(ns transwarp.loader
  (:require [clojure.string :as str]
            [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure-watch.core :refer [start-watch]]
            [clojure.core.async :refer [<!! >!! <! >! chan go]]
            [clostache.parser :refer [render]]
            [digest]
            [taoensso.timbre :as timbre
             :refer [spy info debug error log-env]]))

;; 脚本的目录：./scripts/
;; 启动的时候，加载这个目录下面的所有文件。
;; 如果有文件修改的时候，则重新加载。
;; 每一个加载的模块，都作为一个function。

;; query-name -> {
;;     cache: 缓存时间
;;     result: { hash: { data: *future*, time: "2015-01-01" }}
;; }

(defonce queries (atom {}))
;; pipeline-name -> { func arg-list }
(defonce pipelines (atom {}))
;; interface-name -> { func }
(defonce interfaces (atom {}))
;; source-name -> { func }
(defonce sources (atom {}))

(defn get-script-func
  [node]
  (let [type (:tag node)
        name (-> node :attrs :id)]
    (if (= :pipeline type)
      (@pipelines name)
      (@queries name))))

(defn parse-interface-node
  "传入xml/parse结果中的一个节点。
  返回一个函数。
  执行函数会按照依赖调用本节点及子节点中所有脚本。返回最终的执行结果。"
  [node]
  (let [func (get-script-func node)
        childs (:content node)]
    (if (nil? childs)
      func
      (fn []
        (let [ch (chan)
              procs (for [c childs]
                      (go
                        (>! ch ((parse-interface-node c)))))]
          (apply func (for [_ procs] (<!! ch))))))))

(defn load-interface!
  "副作用函数。传入一个接口名称字符串，接口加载之后，存储的全局的interfaces中。"
  [n]
  (let [root (xml/parse (java.io.FileInputStream. (str "./scripts/interface/" n ".xml")))]
    (parse-interface-node root)))

(defn load-pipeline!
  "加载管道。"
  [n]
  (do
    (load-file (str "./scripts/pipeline/" n ".clj"))
    (let [pipeline (resolve (symbol (str "scripts.pipeline." n "/process")))]
      (swap! pipelines assoc n pipeline))))

(defn load-source!
  "加载数据源。"
  [n]
  (do
    (load-file (str "./scripts/source/" n ".clj"))
    (let [source (resolve (symbol (str "scripts.source." n "/execute")))]
      (swap! sources assoc n source)))) 

(defn query-wrapper
  "返回一个函数。
  调用该函数，即调用查询。
  该查询具有缓存，在缓存时间内，或相同查询进行中重复查询，只会实际执行一次，返回相同的结果。"
  [name statement config]
  (let [execute (@sources (config "source"))
        query (fn [] (execute statement))
        query-status (atom {:func query :cache (Integer/parseInt (config "cache")) :result {}})]
    (fn []
      (info @query-status)
      (let [hashkey (digest/md5 statement)
            q (-> @query-status :result (get hashkey))]
        (if (nil? q)
          (let [q-future (future (query))]
            (swap! query-status assoc-in [:result hashkey] q-future)
            @q-future)
          @q)))))

(defn load-query!
  "加载查询。读取查询的语句，解析注释的内容。
  注释中可以指定：
  cache 缓存时间
  source 数据源的名称
  根据数据源的名称，调用不同的数据源进行查询。
  根据缓存时间，按照数据源和查询语句的md5做key值，在redis中缓存。"
  [n]
  (with-open [rdr (clojure.java.io/reader (str "./scripts/query/" n ".sql"))]
    (let [lines (line-seq rdr)
          {comments true statements false} (group-by #(.startsWith % "#") lines)
          statement (str/join "\n" statements)
          config (apply array-map
                        (flatten 
                          (map #(drop 1 (re-find #"^# *(.+) *: *(.+)" %)) comments)))]
      (swap! queries assoc n (query-wrapper n statement config)))))

(defn load-scripts
  "加载所有的配置文件。"
  []
  (do
    (load-file "./scripts/main.clj")
    (let [scripts @(resolve 'scripts/scripts-in-use)]
      (for [x (:source scripts)]
        (load-source! x))
      (for [x (:pipeline scripts)]
        (load-pipeline! x))
      (for [x (:query scripts)]
        (load-query! x)))))

(defn monitor-scripts
  "监听所有文件的变化。"
  [path]
  (info "watch directory:" path)
  (start-watch [{:path path
                 :event-types [:create :modify :delete]
                 :bootstrap (fn [path] (info "start watch:" path))
                 :callback (fn [event filename] (info event filename))
                 :options {:recursive true}}]))


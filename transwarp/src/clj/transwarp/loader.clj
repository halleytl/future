(ns transwarp.loader
  (:require [clojure-watch.core :refer [start-watch]]
            [taoensso.timbre :as timbre
             :refer [spy info debug error log-env]]))

;; 脚本的目录：./scripts/
;; 启动的时候，加载这个目录下面的所有文件。
;; 如果有文件修改的时候，则重新加载。
;; 每一个加载的模块，都作为一个function。

;; query-name -> { func result cache last-update arg-list }
(defonce queries (atom {}))
;; pipeline-name -> { func arg-list }
(defonce pipelines (atom {}))
;; interface-name -> { func }
(defonce interfaces (atom {}))
;; source-name -> { func }
(defonce sources (atom {}))

(defn load-pipeline!
  [p]
  (do
    (load-file (str "./scripts/pipeline/" p ".clj"))
    (let [pipeline (resolve (symbol (str "scripts.pipeline." p "/process")))]
      (swap! pipelines assoc (keyword p) pipeline))))

(defn load-source!
  "加载数据源。"
  [s]
  (do
    (load-file (str "./scripts/source/" s ".clj"))
    (let [source (resolve (symbol (str "scripts.source." s "/execute")))]
      (swap! sources assoc (keyword s) source)))) 

(defn load-query!
  "加载查询。读取查询的语句，解析注释的内容。
  注释中可以指定：
    cache 缓存时间
    source 数据源的名称
  根据数据源的名称，调用不同的数据源进行查询。
  根据缓存时间，按照数据源和查询语句的md5做key值，在redis中缓存。"
  [q]
  (with-open [rdr (clojure.java.io/reader (str "./scripts/query/" q ".sql"))]
    (let [lines (line-seq rdr)
          {comments true statements false} (group-by #(or
                                                       (.startsWith % "--")
                                                       (.startsWith % "//")) (spy lines))
          statement (apply str statements)
          config (apply hash-map
                        (flatten 
                          (map #(drop 1 (re-find #"(?:--|//) *(.+) *: *(.+)" %)) comments)))]
      (prn config statement)
      )))

(defn load-scripts
  "加载所有的配置文件。"
  []
  (do
    (load-file "./scripts/main.clj")
    (let [scripts @(resolve 'scripts/scripts-in-use)]
      (for [x (:source scripts)]
        (load-source! x))
      (for [x (:pipeline scripts)]
        (load-pipeline! x)))))

(defn monitor-scripts
  "监听所有文件的变化。"
  [path]
  (info "watch directory:" path)
  (start-watch [{:path path
                 :event-types [:create :modify :delete]
                 :bootstrap (fn [path] (info "start watch:" path))
                 :callback (fn [event filename] (info event filename))
                 :options {:recursive true}}]))


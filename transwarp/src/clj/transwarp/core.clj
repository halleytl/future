(ns transwarp.core
  (:require [transwarp.handler]
            [transwarp.task]
            [clojure.string :as str]
            [me.raynes.conch.low-level :as sh]
            [cheshire.core :as json]
            [clojure.java.jdbc :as j]))

(defn hello [] "hello")

(defn script-path
  "返回脚本的相对路径。"
  [f]
  (format "../scripts/%s" f))

(defn db-query
  "连接数据库查询数据。
  f是数据库连接配置文件，sql是查询语句。
  返回查询结果。"
  [f sql]
  (let [config-path (script-path f)
        config      (json/parse-string (slurp config-path))
        {type "type"
         host "host"
         port "port"
         user "user"
         pswd "pswd"
         db "db"} config
        db-spec  {:subprotocol type
               :subname (format "//%s/%s" host db)
               :user user
               :password pswd}]
    (j/query db-spec
             [sql])))

(defn call-script
  "调用脚本文件。
  f是脚本文件名称（不含路径）。
  返回脚本结果。"
  [f]
  (let [file-ext (last (str/split f #"\."))
        script-path (script-path f)
        proc (sh/proc script-path)]
    (sh/done proc)
    (sh/stream-to-string proc :out)))



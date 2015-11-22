(ns scripts.source.test
  (:require [clojure.java.jdbc :as j]))

(defn execute
  "execute the sql statement"
  [sql]
  (let [mysql-db {:subprotocol "mysql"
                  :subname "//127.0.0.1:3306/test"}]
    (j/query mysql-db
             [sql])))


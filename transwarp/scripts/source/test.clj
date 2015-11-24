(ns scripts.source.test
  (:require [clojure.java.jdbc :as j]
            [taoensso.timbre :as timbre
             :refer [spy info debug error log-env]]))

(defn execute
  "execute the sql statement"
  [q]
  (let [mysql-db {:subprotocol "mysql"
                  :subname "//127.0.0.1:3306/test"}]
    (info q)
    (Thread/sleep 10000)
    (j/query mysql-db
             [q])))


(ns transwarp.task
  (:use [clj-xpath.core :only [$x $x:tag $x:text $x:text* $x:attrs $x:attrs* $x:node]])
  (:require [taoensso.timbre :as timbre
             :refer [spy debug error log-env]]
            [clojurewerkz.quartzite.scheduler :as qs]
            [clojurewerkz.quartzite.triggers :as t]
            [clojurewerkz.quartzite.jobs :as j :refer [defjob]]
            [clojurewerkz.quartzite.schedule.simple :refer :all]))

(defn parse-query
  "加载一个查询。"
  [q]
  (let [[data] (slurp (str "../scripts/query/" q))]
    (println "load" q data)))

(defn parse-pipe
  "加载一个管道。"
  [p]
  (let [[data] (slurp (str "../scripts/pipe/" p))]
    (println "load" p)))

(defn parse-interface
  "加载一个接口。"
  [i, cache]
  (try
    (let [data (slurp (str "../scripts/interface/" i ".xml"))
          queries ($x:text* "//q/@id" data)]
      (for [q queries]
        (prn q)))
    (catch java.io.FileNotFoundException e
      ; file not found
      (error (.getMessage e)))))

(defjob NoOpJob
  [ctx]
  (debug "hello"))

(defn init-task []
  (let [s (-> (qs/initialize) qs/start)
        job (j/build
              (j/of-type NoOpJob)
              (j/with-identity (j/key "jobs.noop.1")))
        trigger (t/build
                  (t/with-identity (t/key "triggers.1"))
                  (t/start-now)
                  (t/with-schedule (schedule
                                     (with-repeat-count 10)
                                     (with-interval-in-milliseconds 200))))]
    (qs/schedule s job trigger)))


;host = 192.168.40.109
;port = 6379

(ns my-app (:require [taoensso.carmine :as car :refer (wcar)]))

(def redis-conn {:pool {} :spec {:host "192.168.40.109" 
                                 :port 6379
                                 ;:password "secret"
                                 ;:timeout-ms 6000
                                 ;:db 3
                                 }}
  )

(defmacro wcar* [& body] `(car/wcar redis-conn ~@body))

(wcar* (car/ping)
       (car/set "foo" "bar")
       (car/get "foo"))


(ns transwarp.handler
  (:require [compojure.core :refer [defroutes ANY GET]]))

(defroutes app
  (GET "/" [] "hello"))

(def handler app)

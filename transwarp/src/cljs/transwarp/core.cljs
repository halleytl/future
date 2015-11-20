(ns transwarp.core
  (:require-macros [secretary.core :refer [defroute]])
  (:require [reagent.core :as r]
            [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]))

(enable-console-print!)

(def app-state (r/atom {}))

;; 拦截浏览器的历史事件
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; 定义路由
  (defroutes "/" []
    (swap! app-state assoc :page :dashboard))
  (defroutes "/report" []
    (swap! app-state assoc :page :report))

  (hook-browser-navigation!))

(defn dashboard
  "dashboard页面"
  []
  [:div
   [:h1 "dashboard"]
   [:a {:href "#/report"} "goto report"]])

(defn report
  "视图页面"
  []
  [:div
   [:h1 "report"]
   [:a {:href "#/dashboard"} "goto dashboard"]])

(defmulti current-page #(@app-state :page))
(defmethod current-page :dashboard [] [dashboard])
(defmethod current-page :report [] [report])
(defmethod current-page :default [] [:div "Invalid page"])

(defn ^:export main []
  (app-routes)
  (reagent/render [current-page]
                  (.getElementById js/document "app")))


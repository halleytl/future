(defproject transwarp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.0.6"]
            [lein-npm "0.6.1"]]

  :dependencies [[com.cemerick/piggieback "0.2.1"]
                 [org.clojure/tools.nrepl "0.2.10"]
                 [org.clojure/clojure "1.7.0"]
                 [com.taoensso/timbre "4.1.4"]
                 [org.clojure/clojurescript "1.7.122"]
                 [secretary "1.2.3"]
                 [jayq "2.5.4"]
                 [reagent "0.5.1"]
                 [me.raynes/conch "0.8.0"]
                 [mysql/mysql-connector-java "5.1.37"]
                 [org.clojure/java.jdbc "0.4.2"]
                 [compojure "1.4.0"]
                 [ring "1.4.0"]
                 [cheshire "5.5.0"]
                 [liberator "0.14.0"]
                 [clojurewerkz/quartzite "2.0.0"]
                 [clj-kafka "0.3.3"]
                 [org.clojure/java.jdbc "0.4.2"]
                 [com.taoensso/carmine "2.12.0"]]

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]} 
  
  :ring {:handler transwarp.handler/handler}

  :source-paths ["src/clj"]

  :test-paths ["test/clj"]

  :resource-paths ["resources"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target" "test/js"]

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :output-to     "resources/public/js/app.js"
                             :output-dir    "resources/public/js/out"
                             :source-map    "resources/public/js/out.js.map"
                             :optimizations :none
                             :pretty-print  true}}})

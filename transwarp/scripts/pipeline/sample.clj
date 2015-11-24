(ns scripts.pipeline.sample)

(defn process
  "pipeline的例子"
  [data]
  (map #(assoc % :hello "world") data))

(ns clogue.core
    (:require [clogue.display.ascii-window :as d]
              [clogue.behaviors.take-turn :as t]))

(def entities
  [
    { 
      :id 1
      :kind :entity
      :pos  { :x 1 :y 1 }
      :movement-type :closest-enemy
      :disposition-type :always-hostile
      :glyph "@"
    } 
    { 
      :id 2
      :kind :entity
      :pos  { :x 8 :y 7 }
      :movement-type :closest-enemy
      :disposition-type :always-hostile
      :glyph "&"
    } 
  ]
)

(defn -main []
  (d/init!)
  (loop [state entities 
         turn 5]
    (do 
      (d/draw-state state)
      (if (= 0 turn) (println "done")
          (recur (map #(t/take-turn % state) state) (dec turn)))))
  (d/stop!)
  (System/exit 0))


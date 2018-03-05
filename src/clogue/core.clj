(ns clogue.core
    (:require [clogue.display.ascii-window :as d]
              [clogue.behaviors.take-turn :as t]
              [clogue.input.movement :as m]))

(def entities
  [
    { 
      :id 1
      :kind #{:uncontrolled :entity}
      :pos  { :x 1 :y 1 }
      :movement-type :closest-enemy
      :disposition-type :always-hostile
      :glyph "D"
    } 
    { 
      :id 2
      :kind #{:uncontrolled :entity}
      :pos  { :x 15 :y 10 }
      :movement-type :closest-enemy
      :disposition-type :always-hostile
      :glyph "O"
    } 
    { 
      :id 3
      :kind #{:controlled :entity}
      :pos { :x 10 :y 10 }
      :movement-type :closest-enemy
      :disposition-type :always-hostile
      :glyph "@"
    }
  ]
)

(defn -main []
  (d/init!)
  (loop [state entities 
         turn 100]
    (do 
      ; Draw the game
      (d/draw-state state)
      ; Accept player input
      (let [input (d/await-input)
            updated-state (m/handle-movement-input input state)] 
        ; All uncontrolled entities take turns
        (if (= 0 turn) (println "done")
            (recur (map #(t/take-turn % updated-state) updated-state) 
                   (dec turn))))))
  (d/stop!)
  (System/exit 0))


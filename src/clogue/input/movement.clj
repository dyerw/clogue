(ns clogue.input.movement
    (:require [clogue.utils.coord :as crd]))

(defn get-controlled-entities [entities]
  (filter #((:kind %) :controlled) entities))

(def direction-keybindings
  { 
    \h :west
    \k :north
    \j :south
    \l :east
    \u :northeast
    \y :northwest
    \b :southwest
    \n :southeast
  }
)

(defn handle-movement-input [input entities]
  (let [direction (get direction-keybindings input)
        controlled-entities (get-controlled-entities entities)]
    (concat (clojure.set/difference (into #{} entities)
                                    (into #{} controlled-entities))
            (map #(assoc % :pos
                         (crd/find-adjacent-position direction (:pos %)))
                 controlled-entities))))


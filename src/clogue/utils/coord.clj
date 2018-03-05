(ns clogue.utils.coord
    (:require [clojure.math.numeric-tower :as math]))

(defn sqr [x] (math/expt x 2))

(defn distance [pos1 pos2]
  (let [x1 (:x pos1)
        x2 (:x pos2)
        y1 (:y pos1)
        y2 (:y pos2)]
    (math/sqrt (+ (sqr (- x2 x1)) 
                  (sqr (- y2 y1))))))

(defn direction-toward [pos1 pos2]
  (let [x-direction (- (:x pos2) (:x pos1))
        y-direction (- (:y pos2) (:y pos1))]
    (cond 
      (and (> 0 x-direction) (> 0 y-direction)) :northwest
      (and (< 0 x-direction) (< 0 y-direction)) :southeast
      (and (> 0 x-direction) (< 0 y-direction)) :northeast
      (and (< 0 x-direction) (> 0 y-direction)) :southwest
      (and (= 0 x-direction) (> 0 y-direction)) :south
      (and (= 0 x-direction) (< 0 y-direction)) :north
      (and (> 0 x-direction) (= 0 y-direction)) :east
      (and (< 0 x-direction) (= 0 y-direction)) :west
      :else :none)))

(defn find-adjacent-position [direction pos]
  (condp = direction
    :north     {:x (:x pos) :y (dec (:y pos))}
    :south     {:x (:x pos) :y (inc (:y pos))}
    :west      {:x (dec (:x pos)) :y (:y pos)}
    :east      {:x (inc (:x pos)) :y (:y pos)}
    :northeast {:x (inc (:x pos)) :y (dec (:y pos))}
    :northwest {:x (dec (:x pos)) :y (dec (:y pos))}
    :southeast {:x (inc (:x pos)) :y (inc (:y pos))}
    :southwest {:x (dec (:x pos)) :y (inc (:y pos))}
    :none      pos
    :else      pos))


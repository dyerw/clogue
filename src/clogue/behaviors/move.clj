(ns clogue.behaviors.move
    "Alters maps position key using
    various behaviors"
    (:require [clogue.utils.coord :as crd]
              [clogue.behaviors.disposition :as d]))

(defmulti move :movement-type)

(defmethod move :default [thing] thing)
(defmethod move :closest-enemy [thing entities]
  (let [enemies (filter #(= :hostile (d/disposition thing %)) entities)
        closest-enemy (first (min-key #(crd/distance (:pos thing) (:pos %)) enemies))]
    (-> (crd/direction-toward (:pos thing) (:pos closest-enemy))
        (#(crd/find-adjacent-position % (:pos thing)))
        (#(assoc thing :pos %)))))

;(defmethod move :random [thing entities]
;  (assoc thing :pos (crd/random-adjacent (:pos thing))))


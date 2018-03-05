(ns clogue.behaviors.move
    "Alters maps position key using
    various behaviors"
    (:require [clogue.utils.coord :as crd]
              [clogue.behaviors.disposition :as d]
              [taoensso.timbre :as log]))

(defmulti move :movement-type)

(defmethod move :default [thing] thing)
(defmethod move :closest-enemy [thing entities]
  (log/debug (str "Entity with id " (:id thing) " using :closest-enemy move"))
  (let [enemies (filter #(= :hostile (d/disposition thing %)) entities)
        closest-enemy (first (min-key #(crd/distance (:pos thing) (:pos %)) enemies))]
    (do
      (log/debug (str "Entity with id " (:id thing) " moving towards "
                      "entity with id " (:id closest-enemy)))
      (-> (crd/direction-toward (:pos thing) (:pos closest-enemy))
          (#(crd/find-adjacent-position % (:pos thing)))
          (#(assoc thing :pos %))))))

;(defmethod move :random [thing entities]
;  (assoc thing :pos (crd/random-adjacent (:pos thing))))


(ns clogue.behaviors.take-turn
    (:require ;[clogue.behaviors.attack :as a]
              [clogue.behaviors.move   :as m]))

(defn without-entity [entities entity]
  (filter #(not (= (:id entity) (:id %))) entities))

(defmulti take-turn :kind)

(defmethod take-turn :default [thing entities] thing)
(defmethod take-turn :entity [thing entities]
  (-> thing
      (#(m/move % (without-entity entities thing)))))
      ;a/attack))

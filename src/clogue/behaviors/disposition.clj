(ns clogue.behaviors.disposition)

(defmulti disposition :disposition-type)

(defmethod disposition :always-hostile [thing other-thing]
  :hostile)

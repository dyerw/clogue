(ns clogue.display.terminal
    (:require [clojure.string :as str]))

(defn get-glyph [x y entities]
  (let [entity (first (filter #(and (= (get-in % [:pos :x]) x)
                                    (= (get-in % [:pos :y]) y))
                              entities))]
    (if (nil? entity) "." (:glyph entity))))

(defn get-line [y entities width]
  (map #(get-glyph % y entities) (range width)))

(defn get-lines [entities height width]
  (map #(str/join (get-line % entities width))
       (range height))) 

(defn print-game [entities height width]
  (println (str/join "\n" (get-lines entities height width))))

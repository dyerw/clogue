(ns clogue.display.ascii-window
    (:require [lanterna.screen :as s]
              [taoensso.timbre :as timbre :refer [debug]]))

(def screen (atom nil))

(defn init! []
  (reset! screen (s/get-screen :swing))
  (s/start @screen))

(defn stop! [] (s/stop @screen))

(defn get-glyph [x y entities]
  (let [entity (first (filter #(and (= (get-in % [:pos :x]) x)
                                    (= (get-in % [:pos :y]) y))
                              entities))]
    (if (nil? entity) "." (:glyph entity))))

(defn draw-state [entities]
  (let [size (s/get-size @screen)
        width (first size)
        height (second size)]
    (doseq [x (range width) y (range height)]
        (let [glyph (get-glyph x y entities)]
          (s/put-string @screen x y glyph))))
  (s/redraw @screen))

(defn await-input []
 (s/get-key-blocking @screen))

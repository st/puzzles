(ns day14.core
  (require [st-utils.core :as st]))

(defn distance
  [time [speed flight rest]]
  (let [flight-rest (+ flight rest)
        n-full (quot time flight-rest)
        remain (rem time flight-rest)]
    (* speed (+ (* flight n-full) (min flight remain)))))

(defn read-speed-flight-rest
  [s]
  (->> s
       (re-seq #"\d+")
       (map read-string)))

(defn read-input
  []
  (->> "input.txt"
       st/read-strings
       (map read-speed-flight-rest)))

(defn winner
  []
  (apply max
    (map (partial distance 2503) (read-input))))

(ns day9.core
  (require [st-utils.core :as st]))

(defn distribute
  [e col]
  (map (partial cons e) col))

(defn permutations
  [col]
  (if (= 1 (count col))
    [col]
    (mapcat #(distribute % (permutations (remove #{%} col))) col)))

(defn segments
  [points]
  (partition 2 1 points))

(defn routes
  [cities]
  (map segments (permutations cities)))

;;AlphaCentauri to Snowdin = 66
(defn record-distance
  [distances line]
    (let [tokens (clojure.string/split line #" ")
          start     (-> (nth tokens 0) keyword)
          end       (-> (nth tokens 2) keyword)
          distance  (-> (nth tokens 4) read-string)]
      (assoc-in distances (sort [start end]) distance)))

(def input
  (->>  "input.txt"
        st/read-strings
        (reduce record-distance {})))

(defn cities
  []
  (into #{}
    (concat (keys input)
            (mapcat keys (vals input)))))

(def all-routes
   (routes (cities)))

(defn distance-segment
  [[a b]]
  (get-in input (sort [a b])))

(defn distance
  [route]
  (apply + (map distance-segment route)))

(defn sol1
  []
  (apply min (map distance all-routes)))

(defn sol2
  []
  (apply max (map distance all-routes)))

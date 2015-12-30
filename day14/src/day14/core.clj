(ns day14.core
  (require [st-utils.core :as st]))

(defn distance
  [time [speed flight rest]]
  (let [flight-rest (+ flight rest)
        n-full (quot time flight-rest)
        remain (rem time flight-rest)]
    (* speed (+ (* flight n-full) (min flight remain)))))

(defn distance2
  [time [speed flight rest]]
  (apply + (take time
            (flatten
              (repeat
                (concat (repeat flight speed) (repeat rest 0)))))))

(defn read-speed-flight-rest
  [s]
  (->>  s
        (re-seq #"\d+")
        (map read-string)))

(defn read-name
  [s]
  (->>  s
        (re-find #"^\w+")
        keyword))

(defn record-beast
  [map-beasts s]
  (assoc map-beasts (read-name s) (read-speed-flight-rest s)))

(defn read-input
  []
  (->>  "input.txt"
        st/read-strings
        (reduce record-beast {})))

(def map-beasts
  (read-input))

(def beasts-names
  (keys map-beasts))

(defn distance-beast
  [t beast-name]
  (distance t (beast-name map-beasts)))

(defn distance-beasts
  ([]
   (map distance-beasts (rest (range))))
  ([t]
   (zipmap beasts-names (map (partial distance-beast t) beasts-names))))

(defn add-lead
  [m]
  (into {} (map (fn[[b d]] [b [d 0]]) m)))

(defn best-distance
  [perfs]
  (apply max (map first (vals perfs))))

(defn award-if-best
  [best [b [d s]]]
  (if (= d best)
    [b [d (inc s)]]
    [b [d s]]))

(defn award
  [perfs]
  (let [best (best-distance perfs)]
    (into {} (map (partial award-if-best best) perfs))))

(defn apply-award
  [perfs-times]
  (map award perfs-times))

(defn add-award
  [already new]
  (if (empty? already)
    new
    (into {} (map (fn[[b [d s]]] [b [d (+ (second (b already)) s)]]) new))))

(defn reduce-award
  [perfs-times]
  (reduce add-award {} perfs-times))

(defn best-score
  [perfs]
  (apply max (map second (vals perfs))))

(defn winner
  []
  (apply max
    (map (partial distance 2503) (read-input))))

(ns day10.core)

(defn say-seq
  [xs]
  (str (count xs) (first xs)))

(defn say
  [s]
  (->>  s
        (partition-by identity)
        (map say-seq)
        (apply str)))

(defn iterate-say
  [s n]
  (->> (iterate say s)
       (take (inc n))
       last))

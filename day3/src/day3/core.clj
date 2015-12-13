(ns day3.core)

(defn move
  [[x y] m]
  (case m
    \< [(dec x) y]
    \> [(inc x) y]
    \^ [x (inc y)]
    \v [x (dec y)]))

(defn add-move
  [res dir]
  (conj res (move (last res) dir)))

(defn houses
  [s]
  (reduce add-move [[0 0]] (seq s)))

(defn nb-diff
  [hs]
  (count (group-by identity hs)))

(defn nb-diff-houses
  [ds]
  (nb-diff (houses ds)))

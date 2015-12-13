(ns day1.core
  (require
    [st-utils.core :as su]))

(defn one-move
  [start dep]
  (if (= \) dep)
    (dec start)
    (inc start)))

(defn add-one-move
  [path dep]
  (let [[index place] (last path)
        new-place     [(inc index) (one-move place dep)]]
    (conj path new-place)))

(defn all-moves
  ([path deps]
   (reduce add-one-move path deps))
  ([]
   (->> "input.txt"
        su/read-strings
        first
        (all-moves [[1 0]])))
  ([deps]
    (all-moves [[1 0]] deps)))

(defn sol
  []
  (-> (all-moves)
      last
      last))

(defn index-when-reaches-minus-1
  [[index place]]
  (when (= -1 place)
    index))

(defn sol2
  []
  (->>  (all-moves)
        (some index-when-reaches-minus-1)
        dec))

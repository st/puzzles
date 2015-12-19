(ns day12.core
  (require
    [st-utils.core      :as st]
    [clojure.data.json  :as json]))

(defn sum-only-numbers
  [s]
  (->> s
       (re-seq #"[-]?\d+")
       (map read-string)
       (apply +)))

(defn sol
  []
  (-> "input.txt"
      st/read-one-string
      sum-only-numbers))

(defn has-val?
  [m v]
  ((set (vals m)) v))

(defn s->map
  [i]
  (-> i
      st/read-one-string
      json/read-str))

(defn val-elt
  [forbidden e]
  (cond
    (string? e) 0
    (number? e) e
    (empty? e)  0
    (vector? e) (apply + (map (partial val-elt forbidden) e))
    (map? e)    (if (has-val? e forbidden)
                  0
                  (apply + (map (partial val-elt forbidden) (vals e))))))

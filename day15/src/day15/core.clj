(ns day15.core
  (require
    [st-utils.core :as st]))


(defn ingredient
  [s]
  (->>  (re-seq #"[-]?\d+" s)
        (take 4)
        (map read-string)))

(defn add-ingredient
  [props ingredient]
  (map conj props ingredient))

(defn read-props
  []
  (let [strings     (st/read-strings "input.txt")
        ingredients (map ingredient strings)
        nb-props    (count (first ingredients))]
    (reduce add-ingredient (repeat nb-props []) ingredients)))

(defn cartesian-2
  [xs ys]
  (for [x xs y ys]
    (flatten [x y])))

(defn cartesian
  [xss]
  (reduce cartesian-2 [[]] xss))

(defn distribute
  [n size]
  (for [e (cartesian (repeat n (range 1 (inc size))))
        :when  (= size (apply + e))]
    e))

(defn add-one-at-place
  [v i]
  (concat
    (take i v)
    [(inc (first (drop i v)))]
    (drop (inc i) v)))

(defn add-one-all-places
  [xs]
  (for [i (range (count xs))]
    (add-one-at-place xs i)))

(defn flow
  [v]
  (let [a (dec (first v))]
    (when (pos? a)
      (set (map (partial cons a) (add-one-all-places (rest v)))))))

(defn seed
  [n size]
  (cons (- size (dec n)) (repeat (dec n) 1)))

(defn expand
  [seeds]
  (let [new-seeds (reduce into #{} (map flow seeds))]
    (if (empty? new-seeds)
      seeds
      (into seeds (expand new-seeds)))))

(defn distribute-fast
  [n size]
  (expand #{(seed n size)}))

(defn score-prop
  [spoons-combination props-per-ingredient]
  (->>  (map * spoons-combination props-per-ingredient)
        (apply +)
        (max 0)))

(defn score
  [spoons ingredients]
  (apply * (map (partial score-prop spoons) ingredients)))

(defn max-scores
  []
  (let [ingredients (read-props)]
    (apply max
      (for [spoons (distribute-fast 4 100)]
        (score spoons ingredients)))))

(ns day17.core)

(defn distribute
  [e xs]
  (concat xs (map (partial cons e) xs)))

(defn combinations
  ([xs]
    (combinations xs [[]]))
  ([xs res]
   (if (empty? xs)
    res
    (recur  (rest xs)
            (distribute (first xs) res)))))

(def numbers [33 14 18 20 45 35 16 35 1 13 18 13 50 44 48 6 24 41 30 42])

(defn weight
  [combination]
  (apply + combination))

(defn weight?
  [w combination]
  (= w (weight combination)))

(defn matching
  [ns expected]
  (->> ns
       combinations
       (filter (partial weight? expected))))

(defn sol1
  [ns expected]
  (->> (matching ns expected)
       count))

(defn sol2
  [ns expected]
  (->> (matching ns expected)
       (group-by count)
       sort
       first
       second
       count))

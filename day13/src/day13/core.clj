(ns day13.core
  (require [st-utils.core :as st]))

(defn distribute
  [e col]
  (map (partial cons e) col))

(defn permutations
  [col]
  (if (= 1 (count col))
    [col]
    (mapcat #(distribute % (permutations (remove #{%} col))) col)))

(defn shift-left
  [col]
  (concat (rest col) [(first col)]))

(defn shift-right
  [col]
  (concat [(last col)] (drop-last col)))

(defn pref-value
  [prefs person other]
  (if-let [res (get-in prefs [person other])]
    res
    0))

(defn score-table
  [prefs table]
  (apply +
    (map #(+ (pref-value prefs %1 %2)
             (pref-value prefs %1 %3))
          table (shift-left table) (shift-right table))))

(defn negate
  [n b]
  (if b (- n) n))

;; "Bob would lose 15 happiness units by sitting next to David."
(defn record-pref
  [prefs pref]
  (let [tokens (clojure.string/split pref #" ")
        person (-> (nth tokens 0) keyword)
        lose?  (= "lose" (nth tokens 2))
        value  (-> (nth tokens 3) read-string (negate lose?))
        other  (->> (nth tokens 10) drop-last (apply str) keyword)]
    (assoc-in prefs [person other] value)))

(def prefs
  (->>  "input.txt"
        st/read-strings
        (reduce record-pref {})))

(def persons (keys prefs))

(defn best-score
  ([]
   (best-score persons))
  ([people]
  (let [tables (permutations people)
        scores (map (partial score-table prefs) tables)]
    (apply max scores))))

(ns day11.core)

(defn inc-digit
  [d]
  (-> d
      int
      (- 97)
      inc
      (mod 26)
      (+ 97)
      char))

(defn inc-digits
  [digits]
  (let [new-digit (inc-digit (first digits))]
    (if (= \a new-digit)
      (cons new-digit (inc-digits (rest digits)))
      (cons new-digit (rest digits)))))

(defn inc-number
  [n]
  (apply str (reverse (inc-digits (reverse n)))))

(defn no-iol?
  [n]
  (not (some #(#{\i \o \l} %) n)))

(defn three-consecutives?
  [n]
  (let [diffs (map - (map int (rest n)) (map int n))
        seqs  (partition-by identity diffs)]
    (some #(and (= 1 (first %))
                (< 1 (count %))) seqs)))

(defn repeat-twice?
  [n]
  (->> n
       (partition-by identity)
       (filter #(> (count %) 1))
       (partition-by first)
       count
       (< 1)))

(defn valid?
  [n]
  (and
    (no-iol? n)
    (three-consecutives? n)
    (repeat-twice? n)
    n))

(defn next-pwd
  [n]
  (some valid? (rest (iterate inc-number n))))

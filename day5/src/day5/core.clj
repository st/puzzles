(ns day5.core
  (require
    [st-utils.core :as su]))

(defn vowel?
  [c]
  ((set "aeiou") c))

(defn count-vowels
  [s]
  (->> s
       (filter vowel?)
       count))

(defn at-least-3-vowels?
  [s]
  (>= (count-vowels s) 3))

(defn repetitions
  [s]
  (->>  s
        (partition-by identity)
        (map count)))

(defn letter-doubled?
  [s]
  (some #(> % 1) (repetitions s)))

(defn no-forbidden?
  [s]
  (not-any? #(.contains s %) ["ab" "cd" "pq" "xy"]))

(defn compose-preds?
  [& preds]
  (fn[x] (every? #(% x) preds)))

(def nice?
  (compose-preds? at-least-3-vowels? letter-doubled? no-forbidden?))

(defn count-nice
  [text pred]
  (->>  text
        su/read-strings
        (filter pred)
        count))

(defn contains-predicate?
  [s pred? min-length]
    (loop [letters (seq s)]
      (and
        (>= (count letters) min-length)
          (or
            (pred? letters)
            (recur (rest letters))))))

(defn- first-pair-repeated?
  [letters]
  (let [s (apply str letters)]
    (.contains (subs s 2) (subs s 0 2))))

(defn- repeat-with-between?
  [[a b c & _]]
    (= a c))

(defn repeated-pair?
  [s]
  (contains-predicate? s first-pair-repeated? 4))

(defn contains-repeat-with-between?
  [s]
  (contains-predicate? s repeat-with-between? 3))

(def nice2? (compose-preds? repeated-pair? contains-repeat-with-between?))

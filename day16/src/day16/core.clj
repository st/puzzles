(ns day16.core
  (require [st-utils.core :as st]))

;; Sue 1: goldfish: 9, cars: 0, samoyeds: 9
(defn record-aunt
  [aunts line]
  (let [tokens    (->  line
                        (clojure.string/replace #"[,:]" "")
                        (clojure.string/split #"\s+"))
        number    (-> (nth tokens 1) keyword)
        props     (nnext tokens)
        ks        (->> props (take-nth 2) (map keyword))
        vs        (->> props rest (take-nth 2) (map read-string))]
    (merge aunts {number (zipmap ks vs)})))

(def input
  (->>  "input.txt"
        st/read-strings
        (reduce record-aunt {})))

(defn match?
  [machine [aunt memory]]
  (when
    (every? (fn[[k v]] (= v (k machine))) memory)
    aunt))

(defn match-prop
  [k vmem vmachine]
  (let [op  (case k (:cats :trees) >
                    (:pomeranians :goldfish) <
                    =)]
    (op vmem vmachine)))

(defn match-2?
  [machine [aunt memory]]
  (when
    (every? (fn[[k v]] (match-prop k v (k machine))) memory)
    aunt))

(def machine-result
  { :children 3
    :cats 7
    :samoyeds 2
    :pomeranians 3
    :akitas 0
    :vizslas 0
    :goldfish 5
    :trees 3
    :cars 2
    :perfumes 1})

(defn sol
  []
  (some (fn[[k v]] (match? machine-result [k v])) input))

(defn sol-2
  []
  (some (fn[[k v]] (match-2? machine-result [k v])) input))

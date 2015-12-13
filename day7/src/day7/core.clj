(ns day7.core
  (require
    [clojure.string :as string]))

(defn to-16-bits
  [n]
  (if (< n 0)
    (+ 65536 n)
    n))

(defn eval-atom
  [atom context]
  (let [parsed (read-string atom)]
    (cond
      (number? parsed) (to-16-bits parsed)
      (symbol? parsed) ((keyword parsed) context))))

(defn parse
  [s sep]
  (->>  (string/split s sep)
        (map string/trim)))

(defn parse-wire
  [wire]
  (let [tokens (parse wire #"->")]
    [(drop-last tokens) (last tokens)]))

(defn parse-left
  [left]
  (parse left #" "))

(defn to-bit-operator
  [op]
  (let [bit-operator
    (case op
      "NOT"     bit-not
      "AND"     bit-and
      "OR"      bit-or
      "LSHIFT"  bit-shift-left
      "RSHIFT"  bit-shift-right)]
    (comp to-16-bits bit-operator)))

(defn eval-unary-op
  [[op arg] context]
  ((to-bit-operator op) (eval-atom arg context)))

(defn eval-op
  [[a op b] context]
  ((to-bit-operator op) (eval-atom a context) (eval-atom b context)))

(defn eval-left-exp
  [tokens context]
  (condp = (count tokens)
    1 (eval-atom (first tokens) context)
    2 (eval-unary-op tokens context)
    3 (eval-op tokens context)))

(defn apply-wire
  [context wire]
  (let [[[left] right]  (parse-wire wire)
        parsed-left     (parse-left left)
        left-value      (eval-left-exp parsed-left context)
        target          (keyword right)
        result          (assoc context target left-value)]
    result))

(defn extract-vals
  [tokens]
  (condp = (count tokens)
    1 tokens
    2 (rest tokens)
    3 (take-nth 2 tokens)))

(defn unknown?
  [context val]
  (nil? (eval-atom val context)))

(defn unknown-left?
  [context wire]
  (let [[[left] right]  (parse-wire wire)
        parsed-left     (parse-left left)
        vals            (extract-vals parsed-left)]
    (some (partial unknown? context) vals)))

(defn apply-all-wires
  [wires]
  (loop [context {} [wire & next-wires] wires]
    (if (nil? wire)
      context
      (if (unknown-left? context wire)
        (recur context (concat next-wires [wire]))
        (recur (apply-wire context wire) next-wires)))))

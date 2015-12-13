(ns day7.core-test
  (:require [clojure.test :refer :all]
            [day7.core :refer :all]))

(deftest test-eval-atom
  (is (= 123 (eval-atom "123" {})))
  (is (= 65412 (eval-atom "-124" {})))
  (is (= 10 (eval-atom "a" {:a 10}))))

(deftest parse-wire-wire
  (is (= [["x LSHIFT 2"] "f"] (parse-wire "x LSHIFT 2 -> f")))
  (is (= [["123"] "f"]        (parse-wire "123 -> f"))))

(deftest test-parse-left
    (is (= ["x" "LSHIFT" "2"] (parse-left "x LSHIFT 2")))
    (is (= ["123"]            (parse-left "123"))))

(deftest test-eval-left-exp
  (is (= 123 (eval-left-exp ["123"]     {:a 10})))
  (is (= 65527 (eval-left-exp ["NOT" "a"]  {:a 8})))
  (is (= 32 (eval-left-exp ["a" "LSHIFT" "b"]  {:a 8 :b 2}))))

(deftest test-apply-wire
  (is (= {:a 3} (apply-wire {} "3 -> a")))
  (is (= {:a 13 :b 2 :c 52} (apply-wire {:a 13 :b 2} "a LSHIFT b -> c"))))

(deftest test-to-16-bits
  (is (= 123 (to-16-bits    123)))
  (is (= 65535 (to-16-bits  -1))))

(deftest test-small
  (is (=  {:d 72 :e 507 :f 492 :g 114 :h 65412 :i 65079 :x 123 :y 456}
          (apply-all-wires "abc.txt")))
  (is (=  {:a 3 :b 4 :c 7}
          (apply-all-wires "order.txt"))))

(deftest test-unknown-left
  (is (unknown-left? {} "a AND x -> cx"))
  (is (unknown-left? {:a 1} "a AND x -> cx"))
  (is (unknown-left? {:x 1} "a AND x -> cx"))
  (is (not (unknown-left? {:a 1 :x 1} "a AND x -> cx")))

  (is (unknown-left? {} "NOT a -> cx"))
  (is (not (unknown-left? {:a 1} "NOT a -> cx")))

  (is (unknown-left? {} "a -> cx"))
  (is (not (unknown-left? {:a 1} "a -> cx"))))

(deftest test-sol
  (is (= 956 (-> "input.txt"
                 apply-all-wires
                 :a))))

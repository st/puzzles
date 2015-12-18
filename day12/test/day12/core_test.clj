(ns day12.core-test
  (:require [clojure.test :refer :all]
            [day12.core :refer :all]))

(deftest test-sum-only-numbers
  (is (= 6 (sum-only-numbers "[1,2,3]")))
  (is (= 6 (sum-only-numbers "{\"a\":2,\"b\":4}")))
  (is (= 3 (sum-only-numbers "[[[3]]]")))
  (is (= 3 (sum-only-numbers "{\"a\":{\"b\":4},\"c\":-1}")))
  (is (= 0 (sum-only-numbers "{\"a\":[-1,1]}")))
  (is (= 0 (sum-only-numbers "[-1,{\"a\":1}]")))
  (is (= 0 (sum-only-numbers "[]")))
  (is (= 0 (sum-only-numbers "{}"))))

(deftest test-sol
  (is (= 119433 (sol)))
  (is (= 68466  (->>  "input.txt"
                      s->map
                      (val-elt "red")))))

(deftest test-val-elt
  (is (= 0 (val-elt "red" "")))
  (is (= 0 (val-elt "red" [])))
  (is (= 0 (val-elt "red" {})))
  (is (= 1 (val-elt "red" 1)))
  (is (= 6 (val-elt "red" [1 2 3])))
  (is (= 10 (val-elt "red" [1 2 [3 4]])))
  (is (= 3 (val-elt "red" {:a 1 :b 2})))
  (is (= 0 (val-elt "red" {:a 1 :b 2 :c "red"}))))

(deftest test-has-val
  (is (has-val? {:a "red"} "red"))
  (is (not (has-val? {:a "blue"} "red"))))

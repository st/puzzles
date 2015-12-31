(ns day17.core-test
  (:require [clojure.test :refer :all]
            [day17.core :refer :all]))

(deftest test-combinations
  (is (= [[] [1]] (combinations [1])))
  (is (= [[] [1] [2] [2 1]] (combinations [1 2])))
  (is (= 32  (count (combinations [1 2 3 4 5])))))

(deftest test-weight
  (is (= 10 (weight [3 4 3]))))

(deftest test-sol1
  (is (= 4    (sol1 [20 15 10 5 5] 25)))
  (is (= 1304 (sol1 numbers 150))))

(deftest test-sol2
  (is (= 3    (sol2 [20 15 10 5 5] 25)))
  (is (= 18   (sol2 numbers 150))))

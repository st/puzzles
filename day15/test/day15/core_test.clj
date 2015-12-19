(ns day15.core-test
  (:require [clojure.test :refer :all]
            [day15.core :refer :all]))

(deftest test-prop
  (is (= 68 (score-prop [44 56] [-1 2])))
  (is (= 0  (score-prop [44 56] [-1 -2]))))

(deftest test-score
  (is (= 62842880 (score [44 56] [[-1 2] [-2 3] [6 -2] [3 -1]]))))

(deftest test-read-ingredients
  (is (= [
          [3  -3 -1 0]
          [0 3 0  0]
          [0 0 4  -2]
          [-3 0 0 2]]
         (read-props))))

(deftest test-max-scores
  (is (= 222870 (max-scores))))
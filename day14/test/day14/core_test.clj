(ns day14.core-test
  (:require [clojure.test :refer :all]
            [day14.core :refer :all]))

(deftest test-distance
  (is (= 140 (distance 10 [14 10 127])))
  (is (= 140 (distance 11 [14 10 127])))
  (is (= 140 (distance 137 [14 10 127])))
  (is (= 1120 (distance 1000 [14 10 127]))))

(deftest test-read-input
  (is (= [22 8 165] (first (read-input)))))

(deftest test-sol
  (is (= 2696 (winner))))

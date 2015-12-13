(ns day2.core-test
  (:require [clojure.test :refer :all]
            [day2.core :refer :all]))

(deftest test-lwh
  (is (= [15 23 25] (lwh "15x23x25"))))


(deftest test-surface
   (is (= 58 (surface "2x3x4")))
   (is (= 43 (surface "1x1x10"))))

(deftest test-all-surfaces
  (is (= 1606483 (all-surfaces "input.txt"))))

(deftest test-ribbon
  (is (= 34 (ribbon "2x3x4")))
  (is (= 14 (ribbon "1x1x10")))
  (is (= 62 (ribbon "3x4x4"))))

(deftest test-all-ribbons
  (is (= 3842356 (all-ribbons "input.txt"))))

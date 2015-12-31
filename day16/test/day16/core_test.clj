(ns day16.core-test
  (:require [clojure.test :refer :all]
            [day16.core :refer :all]))

(deftest test-input
  (is (= 500 (count input)))
  (is (=  {:vizslas 3 :goldfish 8 :akitas 5}
          (:218 input))))

(deftest test-sol
  (is (= :40 (sol))))

(deftest test-sol-2
  (is (= :241 (sol-2))))

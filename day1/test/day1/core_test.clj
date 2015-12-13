(ns day1.core-test
  (:require [clojure.test :refer :all]
            [day1.core :refer :all]))

(deftest test-one-move
  (is (= -1 (one-move 0 \))))
  (is (=  3 (one-move 4 \))))
  (is (=  5 (one-move 4 \()))
  (is (=  1 (one-move 0 \())))

(deftest test-add-one-move
  (is (= [[1 0] [2 1]] (add-one-move [[1 0]] \( ))))

(deftest test-all-moves
  (is (=  [[1 0] [2 1] [3 2]]
          (all-moves [[1 0]] "(("))))

(deftest test-sol
 (is (= 232 (sol))))

(deftest test-sol2
  (is (= 1783 (sol2))))

(deftest test-small
  (doseq [[i p] (all-moves  [[1 0]] "()())")]
    (println i " : " p)))

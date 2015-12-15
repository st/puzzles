(ns day10.core-test
  (:require [clojure.test :refer :all]
            [day10.core :refer :all]))

(deftest test-say-seq
  (is (= "11" (say-seq [1])))
  (is (= "21" (say-seq [1 1])))
  (is (= "22" (say-seq [2 2]))))

(deftest test-say
  (is (= "11" (say "1")))
  (is (= "31" (say "111"))))

(deftest test-iterate-3
  (is (= "1211" (iterate-say "1" 3)))
  (is (= "132123222113" (iterate-say "3113322113" 1))))

(deftest test-sol
  (is (= 329356  (count (iterate-say "3113322113" 40))))
  (is (= 4666278 (count (iterate-say "3113322113" 50)))))

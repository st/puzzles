(ns day13.core-test
  (:require [clojure.test :refer :all]
            [day13.core :refer :all]))

(deftest test-permutations
  (is (= [[:z]] (permutations [:z])))
  (is (= [[:a :b] [:b :a]] (permutations [:a :b])))
  (is (= 6 (count (permutations [:a :b :c])))))

(deftest test-shift-left
  (is (= [:b :c :d :a] (shift-left [:a :b :c :d]))))

(deftest test-shift-right
  (is (= [:d :a :b :c] (shift-right [:a :b :c :d]))))

(deftest test-score-table
  (is (= 330 (score-table {:a {:b 54 :c -79 :d -2}
                           :b {:a 83 :c -7 :d -63}
                           :c {:a -62 :b 60 :d 55}
                           :d {:a 46 :b -7 :c 41}}
                           [:a :b :c :d]))))

(deftest test-read-input
  (is (= {:Bob 2, :Carol 26, :David -82, :Eric -75,
    :Frank 42, :George 38, :Mallory 39}
    (:Alice prefs))))

(deftest test-persons
  (is (= [:Alice :Bob :Carol :David :Eric :Frank :George :Mallory]
    persons)))

(deftest sol1
  (is (= 733 (best-score))))

(deftest sol2
  (is (= 725 (best-score (cons :me persons)))))

(ns day14.core-test
  (:require [clojure.test :refer :all]
            [day14.core :refer :all]))

(deftest test-distance
  (is (= 140 (distance 10      [14 10 127])))
  (is (= 140 (distance 11      [14 10 127])))
  (is (= 140 (distance 137     [14 10 127])))
  (is (= 1120 (distance 1000   [14 10 127])))
  (is (= 1120 (distance2 1000  [14 10 127]))))

(deftest test-read-input
  (is (= [:Rudolph [22 8 165]] (first (read-input))))
  (is (= [20 4 75] (:Vixen (read-input)))))

(deftest test-read-beasts-names
  (is (=
    [:Rudolph :Cupid :Prancer :Donner :Dasher :Comet :Blitzen :Vixen :Dancer]
    beasts-names)))

(deftest test-distance-beast
  (is (= 66 (distance-beast 3 :Rudolph))))

(deftest test-distance-beasts
  (is (= {:Blitzen 54,
           :Comet 105,
           :Cupid 40,
           :Dancer 35,
           :Dasher 55,
           :Donner 125,
           :Prancer 90,
           :Rudolph 110,
           :Vixen 80}
          (distance-beasts 5)))
  (is (= [{:Blitzen 18,
          :Comet 21,
          :Cupid 8,
          :Dancer 7,
          :Dasher 11,
          :Donner 25,
          :Prancer 18,
          :Rudolph 22,
          :Vixen 20}
        {:Blitzen 36,
        :Comet 42,
        :Cupid 16,
        :Dancer 14,
        :Dasher 22,
        :Donner 50,
        :Prancer 36,
        :Rudolph 44,
        :Vixen 40}]
          (take 2 (distance-beasts)))))

(deftest test-add-lead
  (is (= {:a [10 0] :b [20 0]} (add-lead {:a 10 :b 20}))))

(deftest test-award
  (is (= {:a [10 3] :b [5 3]}
          (award {:a [10 2] :b [5 3]}))))

(deftest test-add-award
  (is (= {:a [20 5] :b [25 8]}
         (add-award
           {:a [10 5] :b [5 7]}
           {:a [20 0] :b [25 1]})))
  (is (= {:a [20 1] :b [15 0]}
         (add-award
           {}
           {:a [20 1] :b [15 0]}))))

(deftest test-sort-max-score
  (is (=
    10
    (best-score {:a [20 10] :b [25 1] :c [22 5]}))))

(deftest sol2
  (is (=
        1084
        (->>  (distance-beasts)
              (take 2503)
              (map add-lead)
              apply-award
              reduce-award
              best-score))))

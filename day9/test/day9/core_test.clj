(ns day9.core-test
  (:require [clojure.test :refer :all]
            [day9.core :refer :all]))

(deftest test-routes
  (is (= [ [[:a :b] [:b :c]]
           [[:a :c] [:c :b]] ] (take 2 (routes [:a :b :c])))))

(deftest test-read-input
  (is (= 113 (get-in input [:Norrath :Tambi])))
  (is (= 113 (distance-segment [:Norrath :Tambi]))))

(deftest test-all-routes
  (is (= '((:Tambi :Straylight)
           (:Straylight :AlphaCentauri)
           (:AlphaCentauri :Tristram)
           (:Tristram :Norrath)
           (:Norrath :Snowdin)
           (:Snowdin :Faerun)
           (:Faerun :Arbre))
          (first all-routes)))
  (is (= 40320 (count all-routes))))

(deftest test-cities
  (is (= [:AlphaCentauri :Arbre :Faerun :Norrath :Snowdin :Straylight :Tambi :Tristram]
         (sort (cities)))))

(deftest test-distance
  (is (= 228 (distance
    '((:AlphaCentauri :Norrath) (:Norrath :Straylight) (:Straylight :Faerun) (:Faerun :Snowdin) (:Snowdin :Tambi) (:Tambi :Arbre) (:Arbre :Tristram))))))

(deftest test-sols
  (is (= 141 (sol1)))
  (is (= 736 (sol2))))

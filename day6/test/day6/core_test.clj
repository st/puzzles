(ns day6.core-test
  (:require [clojure.test :refer :all]
            [day6.core :refer :all]))

(deftest test-actions
  (is (= 1 (do-action :turn-on 0)))
  (is (= 1 (do-action :turn-on 1)))

  (is (= 0 (do-action :turn-off 0)))
  (is (= 0 (do-action :turn-off 1)))

  (is (= 1 (do-action :toggle  0)))
  (is (= 0 (do-action :toggle 1))))

(deftest test-column
  (is (= {0 0 1 0 2 0} (column 3))))

(deftest test-lights
  (is (= {0 {0 0 1 0}
          1 {0 0 1 0}} (lights 2 2))))

(deftest test-action-in-lights
  (is (= {0 {0 0 1 0}
          1 {0 0 1 0}}
        (action-in-lights :turn-off (lights 2 2) [0 1]))))

(deftest test-parse-action
  (is (= [:turn-on [[2 3] [4 6]]]
         (parse-action "turn on 2,3 through 4,6")))
  (is (= [:turn-off [[913 943] [958 953]]]
         (parse-action "turn off 913,943 through 958,953")))
  (is (= [:toggle [[749 672] [973 965]]]
         (parse-action "toggle 749,672 through 973,965"))))

(deftest test-through
  (is (= [ [0 1] [0 2] [1 1] [1 2] [2 1] [2 2] ]
          (through [0 1] [2 2]))))

(deftest test-do-action-through
  (is (= {0 {0 0 1 0 2 0}
          1 {0 0 1 1 2 1}
          2 {0 0 1 0 2 0}}
         (do-action-through (lights 3 3) "turn on 1,1 through 1,2"))))

(deftest test-count-on
  (is (= 0 (count-on (lights 10 10))))
  (is (= 3 (count-on {0 {0 1 1 0 2 0}
                      1 {0 0 1 1 2 1}
                      2 {0 0 1 0 2 0}}))))

; (deftest test-sol
;   (is (= 17836115 (sol))))

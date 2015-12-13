(ns st-utils.core-test
  (:require [clojure.test :refer :all]
            [st-utils.core :refer :all]))

(deftest test-read-strings
  (is (= ["line 1" "line 2"] (read-strings "abc.txt"))))

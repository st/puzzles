(ns day4.core-test
  (:require [clojure.test :refer :all]
            [day4.core :refer :all]))

(deftest test-md5
  (is (= "000001dbbfa3a5c83a2d506429c7b00e" (md5 "abcdef609043"))))
; 
; (deftest test-starts-with-5-zeroes?
;   (is (not (starts-with-5-zeroes? "abcdef")))
;   (is (not (starts-with-5-zeroes? "0000abc")))
;   (is (starts-with-5-zeroes? "00000abc")))

(deftest test-sol-number
  ;; (is (= 282749 (sol-number "yzbqklnj" 5)))
  (is (= 9962624 (sol-number "yzbqklnj"))))

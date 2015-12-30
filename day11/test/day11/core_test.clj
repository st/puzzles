(ns day11.core-test
  (:require [clojure.test :refer :all]
            [day11.core :refer :all]))

(deftest test-inc-digit
  (is (= \b (inc-digit \a)))
  (is (= \c (inc-digit \b)))
  (is (= \a (inc-digit \z))))

(deftest test-inc-number
  (is (= "xy" (inc-number "xx")))
  (is (= "xz" (inc-number "xy")))
  (is (= "ya" (inc-number "xz")))
  (is (= "baaaa" (inc-number "azzzz")))
  (is (= "vzbxkghc" (inc-number "vzbxkghb"))))

(deftest test-valid?
  (is (valid? "abccdd"))
  (is (valid? "abcdffaa"))
  (is (not (valid? "abd")))
  (is (not (valid? "iabc")))
  (is (not (valid? "aobc")))
  (is (not (valid? "abcl")))
  (is (not (valid? "hijklmmn")))
  (is (not (valid? "abbceffg")))
  (is (not (valid? "abbcegjk"))))

(deftest test-next
  (is (= "abcdffaa" (next-pwd "abcdefgh")))
  (is (= "ghjaabcc" (next-pwd "ghijklmn"))))

(deftest test-sol
  (is (= "vzbxxyzz" (next-pwd "vzbxkghb")))
  (is (= "vzcaabcc" (next-pwd "vzbxxyzz"))))

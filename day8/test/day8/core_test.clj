(ns day8.core-test
  (:require [clojure.test :refer :all]
            [day8.core :refer :all]
            [st-utils.core :as su]))

(deftest test-unescape
  (is (= "ab-cd" (unescape "ab\\x12cd")))
  (is (= "de-fg" (unescape "de\\xfffg")))
  (is (= "fg-hi" (unescape "fg\\\"hi")))
  (is (= "abcd" (unescape "abcd")))

  (is (= "yd-" (unescape (get (su/read-strings "a.txt") 0))))
  (is (= "\"aaa-aaa\"" (unescape (get (su/read-strings "abc.txt") 2))))
  )

(deftest test-char-len-file
  (is (= 23 (len-file "abc.txt" char-len))))

(deftest test-men-len-file
  (is (= 11 (len-file "abc.txt" mem-len))))

(deftest test-diff-len-file
  (is (= 12 (len-file "abc.txt" diff-len))))

(deftest sol
  (is (= 1342 (len-file "input.txt" diff-len)))
  (is (= 2074 (len-file "input.txt" diff-escape)))
  )

(deftest test-escape-len
  (is (= 19 (len-file "abc.txt" diff-escape))))

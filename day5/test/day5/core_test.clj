(ns day5.core-test
  (:require
    [clojure.test :refer :all]
    [day5.core :refer :all]))

(deftest test-vowel?
  (map #(is (vowel? %)) "aeiou")
  (map #(is (not (vowel? %))) "bcdfk"))

(deftest test-count-vowel?
  (is (= 2 (count-vowels "fhghgakdkdi"))))

(deftest test-at-least-3-vowels
  (is (at-least-3-vowels? "aei"))
  (is (at-least-3-vowels? "xazegov"))
  (is (at-least-3-vowels? "aeiouaeiouaeiou"))
  (is (not (at-least-3-vowels? "xazegrv"))))

(deftest test-letter-doubled?
  (is (not (letter-doubled? "a")))
  (is (not (letter-doubled? "ab")))
  (is (not (letter-doubled? "aba")))
  (is (letter-doubled? "abaa"))
  (is (letter-doubled? "aabbccdd")))

(deftest test-no-forbidden?
  (is (no-forbidden? "aceqyx"))
  (is (not (no-forbidden? "abcdef"))))

(deftest test-nice?
  (is (nice? "ugknbfddgicrmopn"))
  (is (nice? "aaa"))
  (is (not (nice? "jchzalrnumimnmhp")))
  (is (not (nice? "haegwjzuvuyypxyu")))
  (is (not (nice? "dvszwmarrgswjxmb"))))

(deftest test-solution
  (is (= 238 (count-nice "text.txt" nice?))))

(deftest test-repeat-pair?
  (is (repeated-pair? "abab"))
  (is (repeated-pair? "abcbc"))
  (is (repeated-pair? "aabcdefgaa"))
  (is (repeated-pair? "abxyzaxqwertzuiopasdfgkljéogviuhdgmduémab"))

  (is (not (repeated-pair? "")))
  (is (not (repeated-pair? "aaa")))
  (is (not (repeated-pair? "abc"))))

(deftest test-contains-repeat-with-between?
  (is (contains-repeat-with-between? "xyx"))
  (is (contains-repeat-with-between? "abcdefeghi"))

  (is (not (contains-repeat-with-between? "")))
  (is (not (contains-repeat-with-between? "av")))
  (is (not (contains-repeat-with-between? "avx")))
  (is (not (contains-repeat-with-between? "avvx"))))

(deftest test-solution-2
  (is (= 69 (count-nice "text.txt" nice2?))))

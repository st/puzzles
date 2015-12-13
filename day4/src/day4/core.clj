(ns day4.core
  (require [digest :as digest]))

(defn md5
  [s]
  (digest/md5 s))

; (defn n-zeroes
;   [n]
;   (apply str (take n (repeat "0"))))
;
; (def mem-n-zeroes (memoize n-zeroes))
;
; (defn fn-starts-with-n-zeroes?
;   [n]
;   (fn [s]
;     (= (mem-n-zeroes n) (subs s 0 n))))
;
; (def starts-with-5-zeroes?
;   (fn-starts-with-n-zeroes? 5))

(defn starts-with-6-zeroes?
  [s]
  (= "000000" (subs s 0 6)))

(defn sol-number
  [s]
  (loop [s s res 0]
    (if (starts-with-6-zeroes? (md5 (str s res)))
      res
      (recur s (inc res)))))

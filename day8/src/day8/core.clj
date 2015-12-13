(ns day8.core
  (require
    [clojure.string :as string]
    [st-utils.core :as su]))

(defn char-len
  [s]
  (count s))

(defn unescape
  [s]
  (-> s
      (string/replace #"\\\\"   "-")
      (string/replace #"\\x[0-9a-fA-F]{2}" "-")
      (string/replace #"\\\"" "-")))

(defn escape
  [s]
  (str  "\""
        (-> s
            (string/replace  #"\\"  "\\\\\\\\")
            (string/replace  #"\""   "\\\\\"")
            )
        "\""))

(defn mem-len
  [s]
  (-> s
      unescape
      char-len
      (- 2)))

(defn diff-len
  [s]
  (- (char-len s) (mem-len s)))

(defn escape-len
  [s]
  (-> s
      escape
      count))

(defn diff-escape
  [s]
  (- (escape-len s) (char-len s)))

(defn len-file
  [file f]
  (->>  file
        su/read-strings
        (map f)
        (apply +)))

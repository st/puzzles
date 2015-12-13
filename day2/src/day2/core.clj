(ns day2.core
  (require
    [clojure.java.io :as io]
    [clojure.string :as string]))

(defn read-strings
  [resource-name]
  (->>  resource-name
        io/resource
        slurp
        string/split-lines))

(defn lwh
  [dims]
  (->>  (string/split dims #"x")
        (map read-string)))

(defn surface
  [dims]
  (let [[l w h]   (lwh dims)
        sides     [(* l w) (* l h) (* w h)]
        surfaces  (mapv #(* 2 %) sides)
        slack     (apply min sides)]
      (apply + (conj surfaces slack))))

(defn ribbon
  [dims]
  (let [[l w h] (lwh dims)
        max-len (max l w h)
        wraps   (-> [l w h] sort reverse rest)
        ribbon-wraps  (* 2 (apply + wraps))
        ribbon-bow    (* l w h)]
    (+ ribbon-wraps ribbon-bow)))

(defn sum-all
  [all-dims compute-fn]
  (->>  all-dims
        read-strings
        (map compute-fn)
        (apply +)))

(defn all-surfaces
  [all-dims]
  (sum-all all-dims surface))

(defn all-ribbons
  [all-dims]
  (sum-all all-dims ribbon))

(ns day6.core
  (require [st-utils.core :as su]))

(defn do-action
  [action state]
  (case action
          :turn-on  1
          :turn-off 0
          :toggle   (if (= 1 state) 0 1)))

(defn do-prog-action
  [action value]
  (case action
    :turn-on  (inc value)
    :turn-off (max 0 (dec value))
    :toggle   (+ 2 value)))

(defn column
  [size]
  (zipmap (range size) (repeat size 0)))

(defn lights
  [size-x size-y]
  (zipmap (range size-x) (repeat (column size-y))))

(defn action-in-lights
  [action lights [x y]]
  (update-in lights [x y] (partial do-prog-action action)))

(defn parse-action
  [action-through]
  (let [action (cond  (.startsWith action-through "turn on") :turn-on
                      (.startsWith action-through "turn off") :turn-off
                      :else :toggle)
        [x0 y0 x1 y1] (->>
                        (-> action-through
                            (clojure.string/split #"\D+")
                            rest)
                        (map read-string))]
    [action [[x0 y0] [x1 y1]]]))

(defn through
  [[x0 y0] [x1 y1]]
  (for [x (range x0 (inc x1))
        y (range y0 (inc y1))]
       [x y]))

(defn do-action-through
  [lights action-through]
  (let [ [action [[x0 y0] [x1 y1]]] (parse-action action-through)
          coords (through [x0 y0] [x1 y1])]
    (reduce (partial action-in-lights action) lights coords)))

(defn count-row
  [row]
  (->>  row
        vals
        (apply +)))

(defn count-on
  [lights]
  (->>  lights
        vals
        (map count-row)
        (apply +)))

(defn sol
  []
  (let [actions-through (su/read-strings "input.txt")
        init-lights     (lights 1000 1000)
        end-lights      (reduce do-action-through init-lights actions-through)]
    (count-on end-lights)))

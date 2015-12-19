(ns st-utils.core
  (require
    [clojure.java.io :as io]
    [clojure.string :as string]))

(defn read-one-string
  "Returns a string for the given resource name."
  [resource-name]
  (->> resource-name
       io/resource
       slurp))

(def read-strings
  (comp string/split-lines read-one-string))

(ns st-utils.core
  (require
    [clojure.java.io :as io]
    [clojure.string :as string]))

(defn read-strings
  "Returns an array of strings (one per line) for the given resource name."
  [resource-name]
  (->>  resource-name
        io/resource
        slurp
        string/split-lines))

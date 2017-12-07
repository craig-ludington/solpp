(ns solpp.import
  (:require [clojure.string :as s]
            [clojure.java.shell :as sh]))

(defn map-contracts [dir]
  (->> (-> (sh/sh "find" dir "-name" "*.sol")
           :out
           s/split-lines)
       (map (fn [path]
              {(second (re-matches #"^.*/(.*\.sol)$" path)) (s/split-lines (slurp path))}))
       (reduce into {})))

#_ (map-contracts "/Users/craig/kwhcoin/solpp/contracts")

(defn pragma? [line]
  (and line (re-find #"^pragma .*$" line)))

(defn import? [line]
  (and line (second (re-matches #"^import .*/(.*.sol).*$" line))))

(defn inline* [idx base-name visited]
  (loop [lines        (idx base-name)
         visited      visited
         acc          []]
    (let [line          (first lines)
          import        (import? line)
          novel-import? (and import (not (visited import)))
          skip-pragma?  (when-not (empty? visited)
                          (pragma? line))]
      (when import
        (clojure.pprint/pprint {:processing base-name
                                :visited    visited
                                :import     import
                                :seen?      (visited import)}))
      (cond (not line)   acc

            skip-pragma?  (recur (rest lines)
                                 visited
                                 acc)       

            novel-import? (recur (rest lines)
                                 (conj visited import)
                                 (into acc (inline* idx import (conj visited import))))

            :else         (recur (rest lines)
                                 (conj visited base-name)
                                 (conj acc (first lines)))))))

(defn inline [contracts-path base-file-name]
  (inline* (map-contracts contracts-path) base-file-name #{}))


#_ (remove-ns 'solpp.import)
#_ (inline "/Users/craig/kwhcoin/solpp/contracts"  "Top.sol")

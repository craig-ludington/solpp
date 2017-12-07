(defproject solpp "0.1.0-SNAPSHOT"
  :description "developer tools for working with Solidity source code"
  :url "http://alpheus.me"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot solpp.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

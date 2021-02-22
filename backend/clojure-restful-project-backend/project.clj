(defproject clojure-restful-project-backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [prismatic/schema "1.1.12"]
                 [metosin/compojure-api "2.0.0-alpha30"]
                 [ring/ring-jetty-adapter "1.8.2"]
                 [toucan "1.15.1"]
                 [mysql/mysql-connector-java "8.0.12"]
                 [buddy/buddy-hashers "1.7.0"]
                 [clj-time "0.15.2"]]
  :main ^:skip-aot clojure-restful-project-backend.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

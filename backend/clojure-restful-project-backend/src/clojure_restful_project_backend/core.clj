(ns clojure-restful-project-backend.core
  (:require [toucan.db :as db]
            [toucan.models :as models])
  (:gen-class))

(def database-spec
  {:classname   "com.mysql.cj.jdbc.Driver"
   :subprotocol "mysql"
   :subname     "//localhost:3306/clj_db"
   :user        "root"
   :useSSL      false})

(defn -main
  [& args]
  (db/set-default-db-connection! database-spec)
  (db/set-default-quoting-style! :mysql)
  (models/set-root-namespace! `clojure-restful-project-backend.models)
  (println "Server started!"))

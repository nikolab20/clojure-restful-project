(ns clojure-restful-project-backend.core
  (:require [toucan.db :as db]
            [toucan.models :as models]
            [ring.adapter.jetty :refer [run-jetty]]
            [compojure.api.sweet :refer [api routes]]
            [clojure-restful-project-backend.controller.employeeController :refer [employee-routes]]
            [clojure-restful-project-backend.controller.clientController :refer [client-routes]]
            [clojure-restful-project-backend.controller.taxRateController :refer [tax-rate-routes]]
            [clojure-restful-project-backend.controller.objectOfSaleController :refer [object-of-sale-routes]]
            [clojure-restful-project-backend.controller.carPartController :refer [car-part-routes]]
            [clojure-restful-project-backend.controller.serviceController :refer [service-routes]]
            [clojure-restful-project-backend.controller.invoiceController :refer [invoice-routes]]
            )
  (:gen-class))

(def database-spec
  {:classname   "com.mysql.cj.jdbc.Driver"
   :subprotocol "mysql"
   :subname     "//localhost:3306/clj_db"
   :user        "root"
   :useSSL      false})

(def swagger-config
  {:ui      "/swagger"
   :spec    "/swagger.json"
   :options {:ui   {:validatorUrl nil}
             :data {:info {:version "1.0.0", :title "Clojure restful project"}}}})

(def app (api {:swagger swagger-config} (apply routes
                                               employee-routes
                                               client-routes
                                               tax-rate-routes
                                               object-of-sale-routes
                                               car-part-routes
                                               service-routes
                                               invoice-routes)))

(defn -main
  [& args]
  (db/set-default-db-connection! database-spec)
  (db/set-default-quoting-style! :mysql)
  (models/set-root-namespace! `clojure-restful-project-backend.models)
  (run-jetty app {:port 3000})
  (println "Server started!"))

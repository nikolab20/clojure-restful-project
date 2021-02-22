(ns clojure-restful-project-backend.controller.taxRateController
  (:require [clojure-restful-project-backend.dto.taxRateDto :refer [TaxRateDTO]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [clojure-restful-project-backend.service.taxRateService :refer [create-tax-rate
                                                                            get-tax-rate
                                                                            get-tax-rates
                                                                            update-tax-rate
                                                                            delete-tax-rate]]
            [schema.core :as s]))

(def tax-rate-routes
  [(POST "/taxRates" []
     :tags ["Tax rate"]
     :body [create-tax-rate-req TaxRateDTO]
     (create-tax-rate create-tax-rate-req))
   (GET "/taxRates" []
     :tags ["Tax rate"]
     (get-tax-rates))
   (GET "/taxRates/:id" []
     :path-params [id :- s/Int]
     :tags ["Tax rate"]
     (get-tax-rate id))
   (PUT "/taxRates/:id" []
     :tags ["Tax rate"]
     :path-params [id :- s/Int]
     :body [updated-tax-rate TaxRateDTO]
     (update-tax-rate id updated-tax-rate)
     )
   (DELETE "/taxRates/:id" []
     :tags ["Tax rate"]
     :path-params [id :- s/Int]
     (delete-tax-rate id))])

(ns clojure-restful-project-backend.controller.objectOfSaleController
  (:require [clojure-restful-project-backend.dto.objectOfSaleDto :refer [ObjectOfSaleDTO]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [clojure-restful-project-backend.service.objectOfSaleService :refer [create-object-of-sale
                                                                                 get-object-of-sale
                                                                                 get-objects-of-sale
                                                                                 update-object-of-sale
                                                                                 delete-object-of-sale]]
            [schema.core :as s]))

(def object-of-sale-routes
  [(POST "/objectsOfSale" []
     :tags ["Object of sale"]
     :body [create-object-of-sale-req ObjectOfSaleDTO]
     (create-object-of-sale create-object-of-sale-req))
   (GET "/objectsOfSale" []
     :tags ["Object of sale"]
     (get-objects-of-sale))
   (GET "/objectsOfSale/:id" []
     :path-params [id :- s/Int]
     :tags ["Object of sale"]
     (get-object-of-sale id))
   (PUT "/objectsOfSale/:id" []
     :tags ["Object of sale"]
     :path-params [id :- s/Int]
     :body [updated-object-of-sale ObjectOfSaleDTO]
     (update-object-of-sale id updated-object-of-sale)
     )
   (DELETE "/objectsOfSale/:id" []
     :tags ["Object of sale"]
     :path-params [id :- s/Int]
     (delete-object-of-sale id))])

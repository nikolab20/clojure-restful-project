(ns clojure-restful-project-backend.controller.carPartController
  (:require [clojure-restful-project-backend.dto.carPartDto :refer [CarPartDTO]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [clojure-restful-project-backend.service.carPartService :refer [create-car-part
                                                                            get-car-part
                                                                            get-car-parts
                                                                            update-car-part
                                                                            delete-car-part]]
            [schema.core :as s]))

(def car-part-routes
  [(POST "/carParts" []
     :tags ["Car part"]
     :body [create-car-part-req CarPartDTO]
     (create-car-part create-car-part-req))
   (GET "/carParts" []
     :tags ["Car part"]
     (get-car-parts))
   (GET "/carParts/:id" []
     :path-params [id :- s/Int]
     :tags ["Car part"]
     (get-car-part id))
   (PUT "/carParts/:id" []
     :tags ["Car part"]
     :path-params [id :- s/Int]
     :body [updated-car-part CarPartDTO]
     (update-car-part id updated-car-part)
     )
   (DELETE "/carParts/:id" []
     :tags ["Car part"]
     :path-params [id :- s/Int]
     (delete-car-part id))])

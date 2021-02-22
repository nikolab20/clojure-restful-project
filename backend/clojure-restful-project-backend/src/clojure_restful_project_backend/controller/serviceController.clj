(ns clojure-restful-project-backend.controller.serviceController
  (:require [clojure-restful-project-backend.dto.serviceDto :refer [ServiceDTO]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [clojure-restful-project-backend.service.serviceService :refer [create-service
                                                                            get-service
                                                                            get-services
                                                                            update-service
                                                                            delete-service]]
            [schema.core :as s]))

(def service-routes
  [(POST "/services" []
     :tags ["Service"]
     :body [create-service-req ServiceDTO]
     (create-service create-service-req))
   (GET "/services" []
     :tags ["Service"]
     (get-services))
   (GET "/services/:id" []
     :path-params [id :- s/Int]
     :tags ["Service"]
     (get-service id))
   (PUT "/services/:id" []
     :tags ["Service"]
     :path-params [id :- s/Int]
     :body [updated-service ServiceDTO]
     (update-service id updated-service)
     )
   (DELETE "/services/:id" []
     :tags ["Service"]
     :path-params [id :- s/Int]
     (delete-service id))])

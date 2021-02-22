(ns clojure-restful-project-backend.controller.clientController
  (:require [clojure-restful-project-backend.dto.clientDto :refer [ClientDTO]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [clojure-restful-project-backend.service.clientService :refer [create-client
                                                                           get-client
                                                                           get-clients
                                                                           update-client
                                                                           delete-client]]
            [schema.core :as s]))

(def client-routes
  [(POST "/clients" []
     :tags ["Client"]
     :body [create-client-req ClientDTO]
     (create-client create-client-req))
   (GET "/clients" []
     :tags ["Client"]
     (get-clients))
   (GET "/clients/:id" []
     :path-params [id :- s/Int]
     :tags ["Client"]
     (get-client id))
   (PUT "/clients/:id" []
     :tags ["Client"]
     :path-params [id :- s/Int]
     :body [updated-client ClientDTO]
     (update-client id updated-client)
     )
   (DELETE "/clients/:id" []
     :tags ["Client"]
     :path-params [id :- s/Int]
     (delete-client id))])

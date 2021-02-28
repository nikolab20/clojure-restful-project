(ns clojure-restful-project-backend.service.clientService
  (:require [clojure-restful-project-backend.models.client :refer [client]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]))

(defn id->created [id]
  (created (str "/clients/" id) {:id id}))

(defn create-client [create-client-req]
  (->> (db/insert! client create-client-req)
       :id
       id->created))

(defn get-clients []
  (->> (db/select client)
       (ok)))

(defn client->response [client]
  (if client
    (ok client)
    (not-found)))

(defn get-client [client-id]
  (-> (client client-id)
      client->response))

(defn update-client [id updated-client]
  (db/update! client id updated-client)
  (ok id))

(defn delete-client [id]
  (db/delete! client :id id)
  (ok))

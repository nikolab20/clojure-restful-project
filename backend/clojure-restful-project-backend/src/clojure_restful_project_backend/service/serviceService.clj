(ns clojure-restful-project-backend.service.serviceService
  (:require [clojure-restful-project-backend.models.service :refer [service]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]))

(defn id->created [id]
  (created (str "/services/" id) {:id id}))

(defn create-service [create-service-req]
  (->> (db/insert! service create-service-req)
       :id
       id->created))

(defn get-services []
  (->> (db/select service)
       (ok)))

(defn service->response [service]
  (if service
    (ok service)
    (not-found)))

(defn get-service [service-id]
  (-> (service service-id)
      service->response))

(defn update-service [id updated-service]
  (db/update! service id updated-service)
  (ok))

(defn delete-service [id]
  (db/delete! service :id id)
  (ok))

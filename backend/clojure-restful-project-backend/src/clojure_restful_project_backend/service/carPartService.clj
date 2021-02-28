(ns clojure-restful-project-backend.service.carPartService
  (:require [clojure-restful-project-backend.models.carPart :refer [CarPart]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]))

(defn id->created [id]
  (created (str "/carParts/" id) {:id id}))

(defn create-car-part [create-car-part-req]
  (->> (db/insert! CarPart create-car-part-req)
       :id
       id->created))

(defn get-car-parts []
  (->> (db/select CarPart)
       (ok)))

(defn carPart->response [carPart]
  (if carPart
    (ok carPart)
    (not-found)))

(defn get-car-part [car-part-id]
  (-> (CarPart car-part-id)
      carPart->response))

(defn update-car-part [id updated-car-part]
  (db/update! CarPart id updated-car-part)
  (ok id))

(defn delete-car-part [id]
  (db/delete! CarPart :id id)
  (ok))

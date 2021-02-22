(ns clojure-restful-project-backend.service.objectOfSaleService
  (:require [clojure-restful-project-backend.models.objectOfSale :refer [ObjectOfSale]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]))

(defn id->created [id]
  (created (str "/taxRates/" id) {:id id}))

(defn create-object-of-sale [create-object-of-sale-req]
  (->> (db/insert! ObjectOfSale create-object-of-sale-req)
       :id
       id->created))

(defn get-objects-of-sale []
  (->> (db/select ObjectOfSale)
       (ok)))

(defn objectOfSale->response [objectOfSale]
  (if objectOfSale
    (ok objectOfSale)
    (not-found)))

(defn get-object-of-sale [object-of-sale-id]
  (-> (ObjectOfSale object-of-sale-id)
      objectOfSale->response))

(defn update-object-of-sale [id updated-object-of-sale]
  (db/update! ObjectOfSale id updated-object-of-sale)
  (ok))

(defn delete-object-of-sale [id]
  (db/delete! ObjectOfSale :id id)
  (ok))

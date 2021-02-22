(ns clojure-restful-project-backend.service.taxRateService
  (:require [clojure-restful-project-backend.models.taxRate :refer [TaxRate]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]))

(defn id->created [id]
  (created (str "/taxRates/" id) {:id id}))

(defn create-tax-rate [create-tax-rate-req]
  (->> (db/insert! TaxRate create-tax-rate-req)
       :id
       id->created))

(defn get-tax-rates []
  (->> (db/select TaxRate)
       (ok)))

(defn taxRate->response [taxRate]
  (if taxRate
    (ok taxRate)
    (not-found)))

(defn get-tax-rate [tax-rate-id]
  (-> (TaxRate tax-rate-id)
      taxRate->response))

(defn update-tax-rate [id updated-tax-rate]
  (db/update! TaxRate id updated-tax-rate)
  (ok))

(defn delete-tax-rate [id]
  (db/delete! TaxRate :id id)
  (ok))


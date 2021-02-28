(ns clojure-restful-project-backend.service.invoiceService
  (:require [clojure-restful-project-backend.models.invoice :refer [Invoice]]
            [clojure-restful-project-backend.models.invoiceItem :refer [InvoiceItem]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]
            [clj-time.format :as f]
            [clj-time.local :as l]))

(defn id->created [id]
  (created (str "/invoices/" id) {:id id}))

(defn add-invoice-items [list-of-items id]
  (doseq [value list-of-items]
    (db/insert! InvoiceItem (assoc value :IDinvoice id)))
  (id->created id))

(defn update-invoice [id updated-invoice]
  (db/update! Invoice id updated-invoice)
  (ok id))

(defn return-today []
  (f/unparse (f/formatter "yyyy-MM-dd") (l/local-now)))

(defn calculateTotal [list-of-items]
  (def totalPrice 0)
  (doseq [value list-of-items]
    (def totalPrice (+ totalPrice (:totalPrice value))))
  :totalPrice
  )

(defn calculateTotalWithTax [list-of-items]
  (def totalPriceWithTax 0)
  (doseq [value list-of-items]
    (def totalPriceWithTax (+ totalPriceWithTax (:totalPrice value))))
  :totalPriceWithTax
  )

(defn create-invoice [create-invoice-req list-of-items]
  (->> (db/insert! Invoice (assoc create-invoice-req :date (return-today)
                                                     :totalPrice ((calculateTotal list-of-items) :totalPrice)
                                                     :totalPriceWithTax ((calculateTotalWithTax list-of-items) :totalPriceWithTax)))
       :id
       (add-invoice-items list-of-items)))

(defn get-invoices []
  (->> (db/select Invoice)
       (ok)))

(defn invoice->response [invoice]
  (if invoice
    (ok invoice)
    (not-found)))

(defn get-invoice [invoice-id]
  (-> (Invoice invoice-id)
      invoice->response))

(defn delete-invoice [id]
  (db/delete! Invoice :id id)
  (ok))

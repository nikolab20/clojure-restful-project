(ns clojure-restful-project-backend.controller.invoiceController
  (:require [clojure-restful-project-backend.dto.invoiceDto :refer [InvoiceDTO]]
            [clojure-restful-project-backend.dto.invoiceItemDto :refer [InvoiceItemDTO]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [clojure-restful-project-backend.service.invoiceService :refer [create-invoice
                                                                            get-invoice
                                                                            get-invoices
                                                                            update-invoice
                                                                            delete-invoice]]
            [schema.core :as s]))


(def invoice-routes
  [(POST "/invoices" []
     :tags ["Invoice"]
     :body [create-invoice-req InvoiceDTO]
     (create-invoice (get create-invoice-req :invoice) (get create-invoice-req :listOfItems)))
   (GET "/invoices" []
     :tags ["Invoice"]
     (get-invoices))
   (GET "/invoices/:id" []
     :path-params [id :- s/Int]
     :tags ["Invoice"]
     (get-invoice id))
   (PUT "/invoices/:id" []
     :tags ["Invoice"]
     :path-params [id :- s/Int]
     :body [updated-invoice InvoiceDTO]
     (update-invoice id updated-invoice)
     )
   (DELETE "/invoices/:id" []
     :tags ["Invoice"]
     :path-params [id :- s/Int]
     (delete-invoice id))])

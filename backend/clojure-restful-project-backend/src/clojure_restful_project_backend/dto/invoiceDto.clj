(ns clojure-restful-project-backend.dto.invoiceDto
  (:require [schema.core :as s]
            [clojure-restful-project-backend.models.invoice :refer [Invoice]]
            [clojure-restful-project-backend.dto.invoiceItemDto :refer [InvoiceItemDTO]]))

(s/defschema InvoiceDTO
  {:invoice     {:date              s/Any
                 :totalPrice        s/Num
                 :totalPriceWithTax s/Num
                 :canceled          s/Int
                 :IDemployee        s/Int
                 :IDclient          s/Int}
   :listOfItems [InvoiceItemDTO]})

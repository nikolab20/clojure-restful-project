(ns clojure-restful-project-backend.dto.invoiceItem
  (:require [schema.core :as s]
            [clojure-restful-project-backend.models.invoiceItem :refer [InvoiceItem]]))

(s/defschema InvoiceItemDTO
  {:idInvoice         s/Int
   :ordinal           s/Int
   :count             s/Int
   :totalPrice        s/Num
   :totalPriceWithTax s/Num
   :measurementUnit   s/Str
   :idObjectOfSale    s/Int})

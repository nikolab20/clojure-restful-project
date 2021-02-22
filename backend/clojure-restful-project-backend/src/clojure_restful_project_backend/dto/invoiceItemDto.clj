(ns clojure-restful-project-backend.dto.invoiceItemDto
  (:require [schema.core :as s]
            [clojure-restful-project-backend.models.invoiceItem :refer [InvoiceItem]]))

(s/defschema InvoiceItemDTO
  {:ordinal           s/Int
   :count             s/Int
   :totalPrice        s/Num
   :totalPriceWithTax s/Num
   :measurementUnit   s/Str
   :IDobjectOfSale    s/Int
   :IDinvoice         s/Int})

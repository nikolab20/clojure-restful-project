(ns clojure-restful-project-backend.dto.invoice
  (:require [schema.core :as s]
            [clojure-restful-project-backend.models.invoice :refer [Invoice]]))

(s/defschema InvoiceDTO
  {:date                s/Any
   :totalPrice          s/Num
   :totalPriceWithPrice s/Num
   :canceled            s/Bool
   :idEmployee          s/Int
   :idClient            s/Int})

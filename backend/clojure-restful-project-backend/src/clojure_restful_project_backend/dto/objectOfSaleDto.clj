(ns clojure-restful-project-backend.dto.objectOfSaleDto
  (:require [schema.core :as s]
            [clojure-restful-project-backend.models.objectOfSale :refer [ObjectOfSale]]))

(s/defschema ObjectOfSaleDTO
  {:price        s/Num
   :priceWithTax s/Num
   :idTaxRate    s/Int})


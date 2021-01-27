(ns clojure-restful-project-backend.dto.taxRateDto
  (:require [schema.core :as s]
            [clojure-restful-project-backend.models.taxRate :refer [TaxRate]]))

(s/defschema TaxRateDTO
  {:tag s/Str
   :value s/Num})

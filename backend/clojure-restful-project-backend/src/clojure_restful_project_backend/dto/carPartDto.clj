(ns clojure-restful-project-backend.dto.carPartDto
  (:require [schema.core :as s]
            [clojure-restful-project-backend.models.carPart :refer [CarPart]]))

(s/defschema CarPartDTO
  {:name           s/Str
   :manufacturer   s/Str
   :description    s/Str
   :idObjectOfSale s/Int
   :stock          s/Int})

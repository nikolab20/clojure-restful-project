(ns clojure-restful-project-backend.dto.serviceDto
  (:require [schema.core :as s]
            [clojure-restful-project-backend.models.service :refer [service]]))

(s/defschema ServiceDTO
  {:name           s/Str
   :description    s/Int
   :idObjectOfSale s/Int})

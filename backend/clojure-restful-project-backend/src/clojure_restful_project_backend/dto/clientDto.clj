(ns clojure-restful-project-backend.dto.clientDto
  (:require [schema.core :as s]
            [clojure-restful-project-backend.models.client :refer [Client]]))

(s/defschema ClientDTO
  {:firstName      s/Str
   :lastName       s/Str
   :numberOfVisits s/Int
   :debt           s/Num})

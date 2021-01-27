(ns clojure-restful-project-backend.dto.employeeDto
  (:require [schema.core :as s]
            [clojure-restful-project-backend.models.employee :refer [Employee]]))

(s/defschema EmployeeDTO
  {:firstName   s/Str
   :lastName    s/Str
   :address     s/Str
   :phoneNumber s/Str
   :email       s/Str
   :username    s/Str
   :password    s/Str})

(s/defschema EmployeeLogInDTO
  {:username s/Str
   :password s/Str})

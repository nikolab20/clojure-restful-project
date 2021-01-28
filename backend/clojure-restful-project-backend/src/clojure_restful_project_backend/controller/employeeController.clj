(ns clojure-restful-project-backend.controller.employeeController
  (:require [clojure-restful-project-backend.dto.employeeDto :refer [EmployeeDTO]]
            [compojure.api.sweet :refer [GET POST PUT DELETE]]
            [clojure-restful-project-backend.service.employeeService :refer [create-employee
                                                                             get-employees
                                                                             get-employee
                                                                             update-employee
                                                                             delete-employee]]
            [schema.core :as s]))

(def employee-routes
  [(POST "/employees" []
     :tags ["employee"]
     :body [create-employee-req EmployeeDTO]
     (create-employee create-employee-req))
   (GET "/employees" []
     :tags ["employee"]
     (get-employees))
   (GET "/employees/:id" []
     :path-params [id :- s/Int]
     :tags ["employee"]
     (get-employee id))
   (PUT "/employees/:id" []
     :tags ["employee"]
     :path-params [id :- s/Int]
     :body [updated-employee EmployeeDTO]
     (update-employee id updated-employee)
     )
   (DELETE "/employees/:id" []
     :tags ["employee"]
     :path-params [id :- s/Int]
     (delete-employee id))])

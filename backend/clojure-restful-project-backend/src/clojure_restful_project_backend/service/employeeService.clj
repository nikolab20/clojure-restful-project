(ns clojure-restful-project-backend.service.employeeService
  (:require [clojure-restful-project-backend.models.employee :refer [Employee]]
            [toucan.db :as db]
            [ring.util.http-response :refer [ok not-found created]]
            [clojure-restful-project-backend.service.commonService :refer [crypt-password]]))

(defn id->created [id]
  (created (str "/employees/" id) {:id id}))

(defn create-employee [create-employee-req]
  (->> (crypt-password create-employee-req)
       (db/insert! Employee)
       :id
       id->created))

(defn get-employees []
  (->> (db/select Employee)
       (map #(dissoc % :password))
       (ok)))

(defn employee->response [employee]
  (if employee
    (ok employee)
    (not-found)))

(defn get-employee [employee-id]
  (-> (Employee employee-id)
      (dissoc :password)
      employee->response))

(defn update-employee [id updated-employee]
  (db/update! Employee id (crypt-password updated-employee))
  (ok))

(defn delete-employee [id]
  (db/delete! Employee :id id)
  (ok))

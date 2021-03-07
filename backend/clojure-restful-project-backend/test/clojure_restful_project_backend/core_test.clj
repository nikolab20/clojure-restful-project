(ns clojure-restful-project-backend.core-test
  (:require [clojure.test :refer :all]
            [clojure-restful-project-backend.core :refer :all]
            [clojure-restful-project-backend.service.clientService :as clientService]
            [clojure-restful-project-backend.service.employeeService :as employeeService]
            [clojure-restful-project-backend.service.taxRateService :as taxRateService]
            [clojure-restful-project-backend.service.objectOfSaleService :as objectOfSaleService]
            [clojure-restful-project-backend.service.carPartService :as carPartService]
            [clojure-restful-project-backend.service.serviceService :as serviceService]
            [clojure-restful-project-backend.service.invoiceService :as invoiceService]
            [toucan.db :as db]))

(db/set-default-db-connection!
  {:classname   "com.mysql.cj.jdbc.Driver"
   :subprotocol "mysql"
   :subname     "//localhost:3306/clj_db"
   :user        "root"
   :useSSL      false})

(db/set-default-quoting-style! :mysql)

;client

(deftest create-client
  (testing "Test create client"
    (let [client (clientService/create-client {:firstName      "Nikola"
                                               :lastName       "Bakic"
                                               :numberOfVisits 0
                                               :debt           0.0})
          found-client (clientService/get-client ((client :body) :id))]
      (is (= "Nikola" ((found-client :body) :firstname)))
      )
    )
  )

(deftest delete-client
  (testing "Test delete client"
    (let [client (clientService/create-client {:firstName      "Nikola"
                                               :lastName       "Bakic"
                                               :numberOfVisits 0
                                               :debt           0.0})]
      (clientService/delete-client ((client :body) :id))
      (is (= nil ((clientService/get-client ((client :body) :id)) :body)))
      )))

(deftest update-client
  (testing "Test update client"
    (let [client (clientService/create-client {:firstName      "Nik"
                                               :lastName       "Bakic"
                                               :numberOfVisits 0
                                               :debt           0.0})
          update (clientService/update-client ((client :body) :id) {:firstName      "Nikola"
                                                                    :lastName       "Bakic"
                                                                    :numberOfVisits 0
                                                                    :debt           0.0})
          found-client (clientService/get-client (update :body))]
      (is (= "Nikola" ((found-client :body) :firstname)))
      )
    )
  )

(deftest find-all-clients
  (testing "Find all clients"
    (def clientsCount (count ((clientService/get-clients) :body)))
    (clientService/create-client {:firstName      "Nikola"
                                  :lastName       "Bakic"
                                  :numberOfVisits 0
                                  :debt           0.0})
    (is (= (inc clientsCount) (count ((clientService/get-clients) :body))))
    )
  )

;employee

(deftest create-employee
  (testing "Test create employee"
    (let [employee (employeeService/create-employee {:firstName   "Nikola"
                                                     :lastName    "Bakic"
                                                     :address     "Beograd BB"
                                                     :phoneNumber "05643074327"
                                                     :email       "nikola@test.com"
                                                     :username    "nikolab"
                                                     :password    "nikolab"})
          found-employee (employeeService/get-employee ((employee :body) :id))]
      (is (= "nikolab" ((found-employee :body) :username)))
      )
    )
  )

(deftest delete-employee
  (testing "Test delete employee"
    (let [employee (employeeService/create-employee {:firstName   "Nikola"
                                                     :lastName    "Bakic"
                                                     :address     "Beograd BB"
                                                     :phoneNumber "05643074327"
                                                     :email       "nikola@test.com"
                                                     :username    "nikolab"
                                                     :password    "nikolab"})]
      (employeeService/delete-employee ((employee :body) :id))
      (is (= nil ((employeeService/get-employee ((employee :body) :id)) :body)))
      )))

(deftest update-employee
  (testing "Test update employee"
    (let [employee (employeeService/create-employee {:firstName   "Nik"
                                                     :lastName    "Bakic"
                                                     :address     "Beograd BB"
                                                     :phoneNumber "05643074327"
                                                     :email       "nikola@test.com"
                                                     :username    "nikolab"
                                                     :password    "nikolab"})
          update (employeeService/update-employee ((employee :body) :id) {:firstName   "Nikola"
                                                                          :lastName    "Bakic"
                                                                          :address     "Beograd BB"
                                                                          :phoneNumber "05643074327"
                                                                          :email       "nikola@test.com"
                                                                          :username    "nikolab"
                                                                          :password    "nikolab"})
          found-employee (employeeService/get-employee (update :body))]
      (is (= "Nikola" ((found-employee :body) :firstname)))
      )
    )
  )

(deftest find-all-employees
  (testing "Find all employees"
    (def employeesCount (count ((employeeService/get-employees) :body)))
    (employeeService/create-employee {:firstName   "Nikola"
                                      :lastName    "Bakic"
                                      :address     "Beograd BB"
                                      :phoneNumber "05643074327"
                                      :email       "nikola@test.com"
                                      :username    "nikolab"
                                      :password    "nikolab"})
    (is (= (inc employeesCount) (count ((employeeService/get-employees) :body))))
    )
  )

;taxRate

(deftest create-tax-rate
  (testing "Test create tax rate"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))]
      (is (= "PDV" ((found-tax-rate :body) :tag)))
      )
    )
  )

(deftest delete-tax-rate
  (testing "Test delete tax rate"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})]
      (taxRateService/delete-tax-rate ((taxRate :body) :id))
      (is (= nil ((taxRateService/get-tax-rate ((taxRate :body) :id)) :body)))
      )))

(deftest update-tax-rate
  (testing "Test update tax rate"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "P"})
          update (taxRateService/update-tax-rate ((taxRate :body) :id) {:value 20
                                                                        :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate (update :body))]
      (is (= "PDV" ((found-tax-rate :body) :tag)))
      )
    )
  )

(deftest find-all-tax-rates
  (testing "Find all tax-rates"
    (def taxRatesCount (count ((taxRateService/get-tax-rates) :body)))
    (taxRateService/create-tax-rate {:value 20
                                     :tag   "PDV"})
    (is (= (inc taxRatesCount) (count ((taxRateService/get-tax-rates) :body))))
    )
  )

;carPart

(deftest create-car-part
  (testing "Test create car part"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          car-part (carPartService/create-car-part {:name           "Front light"
                                                    :manufacturer   "Valeo"
                                                    :description    "Test"
                                                    :idObjectOfSale ((object-of-sale :body) :id)
                                                    :stock          10})
          found-car-part (carPartService/get-car-part ((car-part :body) :id))]
      (is (= "Front light" ((found-car-part :body) :name)))
      )
    )
  )

(deftest delete-car-part
  (testing "Test delete car part"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          car-part (carPartService/create-car-part {:name           "Front light"
                                                    :manufacturer   "Valeo"
                                                    :description    "Test"
                                                    :idObjectOfSale ((object-of-sale :body) :id)
                                                    :stock          10})]
      (carPartService/delete-car-part ((car-part :body) :id))
      (is (= nil ((carPartService/get-car-part ((car-part :body) :id)) :body)))
      )))

(deftest update-car-part
  (testing "Test update car part"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          car-part (carPartService/create-car-part {:name           "Front"
                                                    :manufacturer   "Valeo"
                                                    :description    "Test"
                                                    :idObjectOfSale ((object-of-sale :body) :id)
                                                    :stock          10})
          update (carPartService/update-car-part ((car-part :body) :id) {:name           "Front light"
                                                                         :manufacturer   "Valeo"
                                                                         :description    "Test"
                                                                         :idObjectOfSale ((object-of-sale :body) :id)
                                                                         :stock          10})
          found-car-part (carPartService/get-car-part (update :body))]
      (is (= "Front light" ((found-car-part :body) :name)))
      )
    )
  )

(deftest find-all-car-parts
  (testing "Find all car parts"
    (def carPartsCount (count ((carPartService/get-car-parts) :body)))
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})]
      (carPartService/create-car-part {:name           "Front light"
                                       :manufacturer   "Valeo"
                                       :description    "Test"
                                       :idObjectOfSale ((object-of-sale :body) :id)
                                       :stock          10})
      (is (= (inc carPartsCount) (count ((carPartService/get-car-parts) :body))))
      )
    )
  )

;service

(deftest create-service
  (testing "Test create service"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        70
                                                                     :priceWithTax (+ 70 (* 70 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          service (serviceService/create-service {:name           "Filter change"
                                                  :description    "Test"
                                                  :idObjectOfSale ((object-of-sale :body) :id)})
          found-service (serviceService/get-service ((service :body) :id))]
      (is (= "Filter change" ((found-service :body) :name)))
      )
    )
  )

(deftest delete-service
  (testing "Test delete service"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          service (serviceService/create-service {:name           "Filter change"
                                                  :description    "Test"
                                                  :idObjectOfSale ((object-of-sale :body) :id)})]
      (serviceService/delete-service ((service :body) :id))
      (is (= nil ((serviceService/get-service ((service :body) :id)) :body)))
      )))

(deftest update-service
  (testing "Test update tax rate"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          service (serviceService/create-service {:name           "Filter"
                                                  :description    "Test"
                                                  :idObjectOfSale ((object-of-sale :body) :id)})
          update (serviceService/update-service ((service :body) :id) {:name           "Filter change"
                                                                       :description    "Test"
                                                                       :idObjectOfSale ((object-of-sale :body) :id)})
          found-service (serviceService/get-service (update :body))]
      (is (= "Filter change" ((found-service :body) :name)))
      )
    )
  )

(deftest find-all-services
  (testing "Find all services"
    (def servicesCount (count ((serviceService/get-services) :body)))
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})]
      (serviceService/create-service {:name           "Filter"
                                      :description    "Test"
                                      :idObjectOfSale ((object-of-sale :body) :id)})
      (is (= (inc servicesCount) (count ((serviceService/get-services) :body))))
      )
    )
  )

;objectOfSale

(deftest create-object-of-sale
  (testing "Test create object of sale"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          found-object-of-sale (objectOfSaleService/get-object-of-sale ((object-of-sale :body) :id))]
      (is (= 50M ((found-object-of-sale :body) :price)))
      )
    )
  )

(deftest delete-object-of-sale
  (testing "Test delete object of sale"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})]
      (objectOfSaleService/delete-object-of-sale ((object-of-sale :body) :id))
      (is (= nil ((objectOfSaleService/get-object-of-sale ((object-of-sale :body) :id)) :body)))
      )))

(deftest update-object-of-sale
  (testing "Test update object of sale"
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        45
                                                                     :priceWithTax (+ 45 (* 45 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          update (objectOfSaleService/update-object-of-sale ((object-of-sale :body) :id) {:price        50
                                                                                          :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                                          :idTaxRate    ((found-tax-rate :body) :id)})
          found-object-of-sale (objectOfSaleService/get-object-of-sale (update :body))]
      (is (= 50M ((found-object-of-sale :body) :price)))
      )
    )
  )

(deftest find-all-objects-of-sale
  (testing "Find all objects of sale"
    (def objectsOfSaleCount (count ((objectOfSaleService/get-objects-of-sale) :body)))
    (let [taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))]
      (objectOfSaleService/create-object-of-sale {:price        50
                                                  :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                  :idTaxRate    ((found-tax-rate :body) :id)}))
    (is (= (inc objectsOfSaleCount) (count ((objectOfSaleService/get-objects-of-sale) :body))))
    )
  )

;invoice

(deftest create-invoice
  (testing "Test create invoice"
    (let [client (clientService/create-client {:firstName      "Nikola" :lastName "Bakic"
                                               :numberOfVisits 0 :debt 0.0})
          found-client (clientService/get-client ((client :body) :id))
          employee (employeeService/create-employee {:firstName   "Nikola" :lastName "Bakic" :address "Beograd BB"
                                                     :phoneNumber "05643074327" :email "nikola@test.com"
                                                     :username    "nikolab" :password "nikolab"})
          found-employee (employeeService/get-employee ((employee :body) :id))
          taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          found-object-of-sale (objectOfSaleService/get-object-of-sale ((object-of-sale :body) :id))
          car-part (carPartService/create-car-part {:name        "Front light" :manufacturer "Valeo"
                                                    :description "Test" :idObjectOfSale ((object-of-sale :body) :id)
                                                    :stock       10})
          invoice (invoiceService/create-invoice {:date              nil :totalPrice 0
                                                  :totalPriceWithTax 0 :canceled 0
                                                  :IDemployee        ((found-employee :body) :id) :IDclient ((found-client :body) :id)
                                                  }
                                                 [{:ordinal           1 :count 5
                                                   :totalPrice        ((found-object-of-sale :body) :price)
                                                   :totalPriceWithTax ((found-object-of-sale :body) :pricewithtax)
                                                   :measurementUnit   "kg" :IDobjectOfSale ((object-of-sale :body) :id)
                                                   :IDinvoice         0
                                                   }]
                                                 )
          found-invoice (invoiceService/get-invoice ((invoice :body) :id))]
      (is (= 300M ((found-invoice :body) :totalpricewithtax)))
      )
    )
  )

(deftest delete-invoice
  (testing "Test delete invoice"
    (let [client (clientService/create-client {:firstName      "Nikola" :lastName "Bakic"
                                               :numberOfVisits 0 :debt 0.0})
          found-client (clientService/get-client ((client :body) :id))
          employee (employeeService/create-employee {:firstName   "Nikola" :lastName "Bakic" :address "Beograd BB"
                                                     :phoneNumber "05643074327" :email "nikola@test.com"
                                                     :username    "nikolab" :password "nikolab"})
          found-employee (employeeService/get-employee ((employee :body) :id))
          taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          found-object-of-sale (objectOfSaleService/get-object-of-sale ((object-of-sale :body) :id))
          car-part (carPartService/create-car-part {:name        "Front light" :manufacturer "Valeo"
                                                    :description "Test" :idObjectOfSale ((object-of-sale :body) :id)
                                                    :stock       10})
          invoice (invoiceService/create-invoice {:date              nil :totalPrice 0
                                                  :totalPriceWithTax 0 :canceled 0
                                                  :IDemployee        ((found-employee :body) :id) :IDclient ((found-client :body) :id)
                                                  }
                                                 [{:ordinal           1 :count 5
                                                   :totalPrice        ((found-object-of-sale :body) :price)
                                                   :totalPriceWithTax ((found-object-of-sale :body) :pricewithtax)
                                                   :measurementUnit   "kg" :IDobjectOfSale ((object-of-sale :body) :id)
                                                   :IDinvoice         0
                                                   }]
                                                 )]
      (invoiceService/delete-invoice ((invoice :body) :id))
      (is (= nil ((invoiceService/get-invoice ((invoice :body) :id)) :body)))
      )))

(deftest update-invoice
  (testing "Test update invoice"
    (let [client (clientService/create-client {:firstName      "Nikola" :lastName "Bakic"
                                               :numberOfVisits 0 :debt 0.0})
          found-client (clientService/get-client ((client :body) :id))
          employee (employeeService/create-employee {:firstName   "Nikola" :lastName "Bakic" :address "Beograd BB"
                                                     :phoneNumber "05643074327" :email "nikola@test.com"
                                                     :username    "nikolab" :password "nikolab"})
          found-employee (employeeService/get-employee ((employee :body) :id))
          taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          found-object-of-sale (objectOfSaleService/get-object-of-sale ((object-of-sale :body) :id))
          car-part (carPartService/create-car-part {:name        "Front light" :manufacturer "Valeo"
                                                    :description "Test" :idObjectOfSale ((object-of-sale :body) :id)
                                                    :stock       10})
          invoice (invoiceService/create-invoice {:date              nil :totalPrice 0
                                                  :totalPriceWithTax 0 :canceled 0
                                                  :IDemployee        ((found-employee :body) :id) :IDclient ((found-client :body) :id)
                                                  }
                                                 [{:ordinal           1 :count 5
                                                   :totalPrice        ((found-object-of-sale :body) :price)
                                                   :totalPriceWithTax ((found-object-of-sale :body) :pricewithtax)
                                                   :measurementUnit   "kg" :IDobjectOfSale ((object-of-sale :body) :id)
                                                   :IDinvoice         0
                                                   }]
                                                 )
          update (invoiceService/update-invoice ((invoice :body) :id) {:date              nil :totalPrice 0
                                                                       :totalPriceWithTax 0 :canceled 1
                                                                       :IDemployee        ((found-employee :body) :id) :IDclient ((found-client :body) :id)
                                                                       })
          found-invoice (invoiceService/get-invoice (update :body))]
      (is (= 1 ((found-invoice :body) :canceled)))
      )
    )
  )

(deftest find-all-invoices
  (testing "Find all invoices"
    (def invoicesCount (count ((invoiceService/get-invoices) :body)))
    (let [client (clientService/create-client {:firstName      "Nikola" :lastName "Bakic"
                                               :numberOfVisits 0 :debt 0.0})
          found-client (clientService/get-client ((client :body) :id))
          employee (employeeService/create-employee {:firstName   "Nikola" :lastName "Bakic" :address "Beograd BB"
                                                     :phoneNumber "05643074327" :email "nikola@test.com"
                                                     :username    "nikolab" :password "nikolab"})
          found-employee (employeeService/get-employee ((employee :body) :id))
          taxRate (taxRateService/create-tax-rate {:value 20
                                                   :tag   "PDV"})
          found-tax-rate (taxRateService/get-tax-rate ((taxRate :body) :id))
          object-of-sale (objectOfSaleService/create-object-of-sale {:price        50
                                                                     :priceWithTax (+ 50 (* 50 (/ ((found-tax-rate :body) :value) 100)))
                                                                     :idTaxRate    ((found-tax-rate :body) :id)})
          found-object-of-sale (objectOfSaleService/get-object-of-sale ((object-of-sale :body) :id))
          car-part (carPartService/create-car-part {:name        "Front light" :manufacturer "Valeo"
                                                    :description "Test" :idObjectOfSale ((object-of-sale :body) :id)
                                                    :stock       10})]
      (invoiceService/create-invoice {:date              nil :totalPrice 0
                                      :totalPriceWithTax 0 :canceled 0
                                      :IDemployee        ((found-employee :body) :id) :IDclient ((found-client :body) :id)
                                      }
                                     [{:ordinal           1 :count 5
                                       :totalPrice        ((found-object-of-sale :body) :price)
                                       :totalPriceWithTax ((found-object-of-sale :body) :pricewithtax)
                                       :measurementUnit   "kg" :IDobjectOfSale ((object-of-sale :body) :id)
                                       :IDinvoice         0
                                       }]
                                     ))
    (is (= (inc invoicesCount) (count ((invoiceService/get-invoices) :body))))
    )
  )

(ns clojure-restful-project-backend.service.commonService
  (:require [buddy.hashers :as hashers]))

(defn crypt-password [req]
  (-> (update req :password hashers/derive)))

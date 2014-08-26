(ns acme.test.handler
  (:require [clojure.test :refer :all]
            [acme.handler :refer :all]
            [ring.mock.request :as mock])
   (:use midje.sweet))

(facts 
  "main route"
  (app (mock/request :get "/")) =>  (contains [[:status 200], [:body  "Hello World"]])
  "not-found route"
  (app (mock/request :get "/invalid")) => (contains [[:status 404]])
)
(ns acme.test.handler
  (:require [clojure.test :refer :all]
            [acme.handler :refer :all]
            [ring.mock.request :as mock])
   (:use midje.sweet))

(facts 
  "main route"
  (app (mock/request :get "/")) =>  (contains [[:status 200], [:body  "Hello World"]])
  "not-found route"
  (:status (app (mock/request :get "/invalid"))) => 404
)
  
(facts 
  "get neighbors"
  (:body (app (mock/request :get "/neighbor/1/1"))) => "[1,1]"
  )


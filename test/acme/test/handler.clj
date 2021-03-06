(ns acme.test.handler
  (:require [clojure.test :refer :all]
            [acme.handler :refer :all]
            [cheshire.core :as json]
            [ring.mock.request :as mock])
   (:use midje.sweet))

(facts 
  "main route"
  (app (mock/request :get "/")) =>  (contains [[:status 200], [:body  "Hello World"]])
  "not-found route"
  (:status (app (mock/request :get "/invalid"))) => 404
)
  
(facts 
  "put strings into collection"
  (cell "1" "1") => [1 1]
  "get neighbors"
  (:body (app (mock/request :get "/neighbor/1/1"))) => "[[2,2],[0,0],[1,0],[0,2],[2,0],[2,1],[1,2],[0,1]]"
  "split params into set"
  (:body (app (-> (mock/request :post "/stepper" (json/generate-string []))  
                (mock/content-type "application/json")))) => "[]"
  (:body (app (-> (mock/request  :post "/stepper" 
              (json/generate-string [[1 0] [1 1] [1 2]]))
                (mock/content-type "application/json")))) => "[[1,1],[2,1],[0,1]]"
  )


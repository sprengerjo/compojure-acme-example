(ns acme.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [clojure.string :as cstr]
            )
  (:use acme.gol)
)

(def data
  {:1 { :name "Bene"
        :email "bene@example.com" },
   :2 { :name "Jonas"
        :email "jonas@example.com" },
   :3 { :name "Stephan"
        :email "stephan@example.com" }})

(defn get-data [key]
  "Gets the value for key from data"
  (data (keyword key)))

(defn cell [x y]
  into [] (map read-string [x y]))


(defn cells [params]
     (set (map (fn [i] (map read-string i)) (filter #(< 1(count %)) 
        (partition-by #(= % " ") (filter #(not= % ",") (map str params)))))))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/data/" [] (response data))
  (GET "/data/:id" [id] (response (get-data id)))
  (GET "/data/:id/:field" [id field] (response ((get-data id) (keyword field))))
; game of live stuff
  (GET "/neighbor/:x/:y" [x y] (response (neighbors (cell x y))))
  (GET "/stepper" {params :params}  (response (step (cells (:cells params)))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
;  (-> (handler/site app-routes)
;      (middleware/wrap-json-body)
;      (middleware/wrap-json-response)))
(middleware/wrap-json-response
  (middleware/wrap-json-body
    (handler/site app-routes))))

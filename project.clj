(defproject acme "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [ring/ring-core "1.2.0"]
                 [ring/ring-json "0.2.0"]
                 [midje "1.6.3"]
                 [cheshire "5.3.1"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler acme.handler/app}
  :profiles
  {:dev {:plugins [[lein-midje "3.1.1"]]
        :dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})

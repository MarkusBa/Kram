(defproject liberator-service "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.4"]
                 [ring-server "0.3.0"]
                 [liberator "0.9.0"]
                 [lib-noir "0.6.9"]
		 [org.clojure/java.jdbc "0.1.1"]
                 [mysql/mysql-connector-java "5.1.26"]
                 [org.hibernate/hibernate-core "4.0.1.Final"] 
                 [cheshire "5.2.0"]]
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler liberator-service.handler/app
         :init liberator-service.handler/init
         :destroy liberator-service.handler/destroy}
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.0"]]}}
  :java-source-paths ["java"]
  :resources-path "resources")
                 

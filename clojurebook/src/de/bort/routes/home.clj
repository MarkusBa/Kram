(ns de.bort.routes.home  
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource]]
            [cheshire.core :refer [generate-string]]
            [clojure.string :refer [split]]
            [noir.io :as io]
            [clojure.java.io :refer [file]]
	    [de.bort.entities.hibernate :as hib]
            )
  (:import [de.bort.entities Prefixes]
           ) )

(defresource home
    :available-media-types ["text/html"]
    
    :exists?
    (fn [context]
      (let [f (file (str (io/resource-path) "/home.html"))]
        [(.exists f) {::file f}]))
    
    :handle-ok
    (fn [{{{resource :resource} :route-params} :request}]
      (file (str (io/resource-path) "/home.html")))
    
    :last-modified
    (fn [{{{resource :resource} :route-params} :request}]
      (.lastModified (file (str (io/resource-path) "/home.html")))))

(defn get-pfx [] 
  (map str (hib/get-entities "Prefixes") )
  )  

(defresource get-prefixes
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (get-pfx)))
  :available-media-types ["application/json"])


;gets a string
(defn add-pfx [text1 text2 ]
  (let [p (Prefixes.)]
    
    (.setUri p text2)
    (.setPrefix p  text1)
      (hib/add-entities p)))

(defresource add-prefix
  :allowed-methods [:post]
  :available-media-types ["application/json"]
  :malformed? (fn [context]
                (let [params (get-in context [:request :form-params])] 
                  (empty? (get params "prefix"))))
  :handle-malformed "prefix name cannot be empty!"
  :post!  
  (fn [context]             
    (let [params (get-in context [:request :form-params])]
      (add-pfx (get params "prefix") (get params "uri"))))
  :handle-created (fn [_]  (generate-string (get-pfx))) 
  ) 
                                        

(defroutes home-routes
  (ANY "/" request home)
  (ANY "/add-prefix" request add-prefix)
  (ANY "/prefixes" request get-prefixes))

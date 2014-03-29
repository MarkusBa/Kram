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

(defn get-pfx [booli] 
  (map str (hib/get-entities "Prefixes" booli) )
  )

(defresource get-prefixes
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (get-pfx "false")))
  :available-media-types ["application/json"])

(defresource get-deleted
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string (get-pfx "true")))
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
  :handle-created (fn [_]  (generate-string (get-pfx "false"))) 
  ) 

(defn cst [ids]
  (map #(Integer/valueOf %) ids))

(defn perform-deletions [text  ]
  (let [ids (cst (split text #"\s"))]
    
    (hib/delete-entity-ids "Prefixes" ids)))


(defresource set-deletions
  :allowed-methods [:post]
  :available-media-types ["application/json"]
  :malformed? (fn [context]
                (let [params (get-in context [:request :form-params])] 
                  (empty? (get params "deletionids"))))
  :handle-malformed "deletionids cannot be empty!"
  :post!  
  (fn [context]             
    (let [params (get-in context [:request :form-params])]
      (perform-deletions (get params "deletionids"))))
  :handle-created (fn [_]  (generate-string (get-pfx "false"))) 
  ) 

(defroutes home-routes
  (ANY "/" request home)
  (ANY "/add-prefix" request add-prefix)
  (ANY "/set-deletions" request set-deletions)
  (ANY "/deletions" request get-deleted)
  (ANY "/prefixes" request get-prefixes))

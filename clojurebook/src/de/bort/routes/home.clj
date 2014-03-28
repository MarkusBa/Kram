(ns de.bort.routes.home  
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource]]
            [cheshire.core :refer [generate-string]]
            [noir.io :as io]
            [clojure.java.io :refer [file]]
	    [de.bort.entities.hibernate :as hib]	))

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

;deleteme
(def prefixes (atom ["foo" "bar"]))

(defresource add-prefix
  :allowed-methods [:post]
  :malformed? (fn [context]
                (let [params (get-in context [:request :form-params])] 
                  (empty? (get params "prefix"))))
  :handle-malformed "prefix name cannot be empty!"
  :post!  
  (fn [context]             
    (let [params (get-in context [:request :form-params])]
      (swap! prefixes conj (get params "prefix"))))
  :handle-created (fn [_] (generate-string (get-pfx))
                    :available-media-types ["application/json"]))

(defroutes home-routes
  (ANY "/" request home)
  (ANY "/add-prefix" request add-prefix)
  (ANY "/prefixes" request get-prefixes))

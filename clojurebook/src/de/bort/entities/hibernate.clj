(ns de.bort.entities.hibernate
  (:import (javax.persistence Id Entity GeneratedValue)
           org.hibernate.annotations.GenericGenerator
           org.hibernate.SessionFactory
           org.hibernate.cfg.Configuration
           de.bort.entities.Nodes
	   de.bort.entities.Prefixes
	   de.bort.entities.Quads
           de.bort.util.Main)
  (:require [clojure.java.jdbc :as jdbc]))

(defonce session-factory
  (delay (-> (Configuration.)
             .configure
             .buildSessionFactory)))

(defmacro with-session
  [session-factory & body]
  `(with-open [~'session (.openSession ~(with-meta session-factory '{:tag SessionFactory}))]
     ~@body))

(defn get-entities
  "A simplified implementation of get-entities, benefitting from the
with-session macro."
  [entitystring deleted]
  (with-session @session-factory
    (-> session
       (.createQuery (str "from " entitystring " where deleted = " deleted))
        .list)))

(defmacro with-transaction
  [& body]
  `(let [tx# (.beginTransaction ~'session)]
     ~@body
     (.commit tx#)))

(defn add-entities
  "A simplified implementation of add-nodes, benefitting from the
with-session and with-transaction macros."
  [& entities]
  (with-session @session-factory
    (with-transaction
      (doseq [entity entities]
        (.save session entity)))))

(defn delete-entity-ids
  "A simplified implementation of add-nodes, benefitting from the
with-session and with-transaction macros."
  [entityname ids]
  (with-session @session-factory
    (with-transaction
      (Main/deleteThese session entityname ids))))

(def db-spec {:classname "com.mysql.jdbc.Driver"
              :subprotocol "mysql"
              :subname "//localhost:3306/crudbook"
              :username "root"
              :password "1234"})

         

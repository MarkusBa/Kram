(ns de.bort.entities.hibernate
  (:import (javax.persistence Id Entity GeneratedValue)
           org.hibernate.annotations.GenericGenerator
           org.hibernate.SessionFactory
           org.hibernate.cfg.Configuration
           de.bort.entities.Nodes
	   de.bort.entities.Prefixes
	   de.bort.entities.Quads))

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


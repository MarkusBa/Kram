(ns de.bort.entities.hibernate
  (:import (javax.persistence Id Entity GeneratedValue)
           org.hibernate.annotations.GenericGenerator
           org.hibernate.SessionFactory
           org.hibernate.cfg.Configuration
           de.bort.entities.Nodes))

(defonce session-factory
  (delay (-> (Configuration.)
             .configure
             .buildSessionFactory)))

(defn add-nodes
  [& nodes]
  (with-open [session (.openSession @session-factory)]
    (let [tx (.beginTransaction session)]
      (doseq [node nodes]
        (.save session node))
      (.commit tx))))


(defmacro with-session
  [session-factory & body]
  `(with-open [~'session (.openSession ~(with-meta session-factory '{:tag SessionFactory}))]
     ~@body))

(defn get-nodes
  "A simplified implementation of get-nodes, benefitting from the
with-session macro."
  []
  (with-session @session-factory
    (-> session
        (.createQuery "from Nodes")
        .list)))

(defmacro with-transaction
  [& body]
  `(let [tx# (.beginTransaction ~'session)]
     ~@body
     (.commit tx#)))

(defn add-nodes
  "A simplified implementation of add-nodes, benefitting from the
with-session and with-transaction macros."
  [& nodes]
  (with-session @session-factory
    (with-transaction
      (doseq [node nodes]
        (.save session node)))))

(ns rdf-jena.core
  (:import (com.hp.hpl.jena.sdb.sql JDBC SDBConnection))
  (:import (com.hp.hpl.jena.sdb StoreDesc))
  (:import (com.hp.hpl.jena.sdb.store DatasetStore StoreFactory LayoutType DatabaseType))
  (:import (java.sql DriverManager))
  (:import (com.hp.hpl.jena.query ResultSet ResultSetFormatter QueryExecution QueryExecutionFactory Query QueryFactory))
   )

(def mysqlDriver "com.mysql.jdbc.Driver")
(def rdffile "/home/markus/Downloads/jena-2.11.0/jena-sdb/examples.n3")

(def dburl "jdbc:mysql://localhost:3306/jena")

(def dbtype "MySQL")

(def dbuser "root")
(def dbpw "test")


(defn -main []
  (do
    (JDBC/loadDriverMySQL)
    (let [jdbc (DriverManager/getConnection dburl dbuser dbpw)
          storeDesc (StoreDesc. LayoutType/LayoutTripleNodesIndex DatabaseType/MySQL)
          query (QueryFactory/create "SELECT DISTINCT ?s { ?s ?p ?o }")
          conn (SDBConnection. jdbc)
          store (StoreFactory/create storeDesc  conn)
          ds (DatasetStore/create store )
          qe (QueryExecutionFactory/create query ds)]
      
      (do
        (try
          
          (ResultSetFormatter/out (.execSelect qe))
          (finally (.close qe)))
        (.close store )
        ))
    
    
    ))

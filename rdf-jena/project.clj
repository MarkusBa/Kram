(defproject rdf-jena "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/java.jdbc "0.0.6"]         ;; jdbc 
                 [mysql/mysql-connector-java "5.1.6"]
                 [org.apache.jena/apache-jena-libs "2.11.0" :extension "pom" ]
                 [org.apache.jena/jena-sdb "1.4.0" ]])

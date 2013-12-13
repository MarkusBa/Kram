(ns modern-cljs.core
  (:require [clojure.java.io :as io]
            [clj-http.client :as client]))

(defn copy [uri file]
  (with-open [in (io/input-stream uri)
              out (io/output-stream file)]
    (io/copy in out)))

(def url "http://www.reddit.com/r/wallpaper.json")

(def test-file
  (client/get "http://placehold.it/350x150" {:as :byte-array}))

(def test-file2 
  (client/get "http://www.google.de" {:as :byte-array}))

(defn write-file []
   (with-open [w (clojure.java.io/output-stream "test-file.gif")]
     (.write w (:body test-file))))

(defn write-file2 []
   (with-open [w (clojure.java.io/output-stream "test-file")]
     (.write w (:body test-file2))))


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

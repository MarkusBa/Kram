(ns modern-cljs.modern
  (:use [domina :only [by-id  set-text! value set-value! append! log ]]
        [domina.css :only [sel]]
        [domina.events :only [listen! capture! listen-once! capture-once!
                              unlisten! dispatch-event! dispatch! unlisten-by-key!
                              get-listeners prevent-default stop-propagation
                              target current-target event-type raw-event]])
  (:require [simple-xhr :as sxhr])   
  )

(sxhr/request
 :url "https://www.google.com/"
  :method "GET"
  :complete
  (fn [xhrio]
    (when (.isSuccess xhrio)
     (let [content (-> xhrio
                      .getResponseJson
                      (js->clj :keywordize-keys true))]
       
        (.log js/console content)))))

;;(defn xhr-connection
;;  "Returns an XhrIo connection"
;;  []
;;  (goog.net.XhrIo.))

;;(def xhr xhr-connection)

;;(defn myCallback [replyValue]
;;  ())
  ;;... Do Something with replyValue
  ;;... for example: (someJsonFunc (.getResponseJson (.target replyValue))))

;;(defn ajax-json [url]
;;   (.send xhr url myCallback))


(defn set-html! [dom content]
  (set! (. dom -innerHTML) content))

(defn set-html! [dom content]
  (set! (. dom -innerHTML) content))

(def link-stringlist (list "https://www.google.com/#q=test" "https://www.google.com/#q=test2"))

(def basislink "https://www.google.com/#q=")

(defn linkify [link] (str "<a href='" basislink link "' > Link </ a>"))

(defn link [linklist] (clojure.string/join "<br />" linklist))

(defn targets [] (let [user-linklist (value (by-id "inputfield"))]
                   (clojure.string/split-lines user-linklist)
                   ))

(defn getvalue [] (value (by-id "inputfield")))

(defn search [] (append! (by-id "linklist") (str "<br />"
                                                 (link (map linkify (targets)) ))))

(listen! (sel "#searchbutton") :click (fn [evt] 
                                                   (search)))

(set-html! (by-id "title") "Google enhanced")




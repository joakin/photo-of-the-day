(ns photo-of-the-day.api
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [map]]
            [clojure.string :refer [join replace]]
            [cljs.pprint :refer [pprint]]
            ))

(extend-type js/NodeList
  ISeqable
  (-seq [array] (array-seq array 0)))

(def domain "https://commons.wikimedia.org/")

(defn potd-url
  "Gets a POTD url, params should be an array of [YEAR MONTH DAY?]"
  [params]
  (str domain "api/rest_v1/page/html/"
       (js/encodeURIComponent
         (str "Template:Potd/" (join "-" params)))))

(defn figure->img
  "Converts a parsoid-dom figure to a map"
  [figure]
  (let [a (.querySelector figure "a")
        img (.querySelector figure "img")
        cap (.querySelector figure "figcaption")
        name (replace (-> img (aget "attributes") (aget "resource") (aget "textContent"))
                      #"^\./" "")]
    {:name name
     :type (keyword (-> img (aget "dataset") (aget "fileType")))
     :url (replace (.-href a)
                   (str (.-origin js/location) (.-pathname js/location))
                   (str domain "wiki/"))
     :thumbnail {:src (.-src img)
                 :width (.-width img)
                 :height (.-height img)}
     :description (.-textContent cap)}))

(defn clean-up
  "If the request is successful, get structured data from the payload.

  Else just return the failed response"
  [{:keys [success body] :as res}]
  (if-not success
    res
    (let [dom (.createElement js/document "div")]
      (set! (.-innerHTML dom) body)
      (let [figures (seq (.querySelectorAll dom "figure"))
            imgs (mapv figure->img figures)]
        ; (pprint (group-by :type imgs))
        imgs))))

(defn get!
  "Gets a POTD page.

  Params should be an array of [YEAR MONTH DAY?]"
  [params]
  (map clean-up
       [(http/get (potd-url params)
                  {:with-credentials? false})]))


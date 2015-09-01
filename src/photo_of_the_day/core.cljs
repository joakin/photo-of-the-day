(ns ^:figwheel-always photo-of-the-day.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.core.async :refer [<!]]
            [cljs.pprint :refer [pprint]]
            [photo-of-the-day.api :as api]
            ))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

;; UI

(defn hello-world []
  [:h1 (:text @app-state)])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

; (go
;   (pprint (<! (api/get-potd! ["2012" "02"]))))
; (go
;   (pprint (<! (api/get-image! "File:20110421_Tbilisi_Georgia_Panoramic.jpg"))))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )


(ns ^:figwheel-always photo-of-the-day.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.core.async :refer [<!]]
            [cljs.pprint :refer [pprint]]
            [photo-of-the-day.api :as api]
            ))

(enable-console-print!)

(defonce app-state (atom {}))

;; UI

(defn hello-world []
  [:h1 (:text @app-state)])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload [])


(ns ^:figwheel-always photo-of-the-day.core
  (:require [reagent.core :as reagent]
            [photo-of-the-day.state :refer [app-state]]
            [photo-of-the-day.ui.app :refer [app]]
            [photo-of-the-day.actions :refer [fetch-current-month-media!]]
            ))

(enable-console-print!)

(reagent/render-component
  [app app-state]
  (. js/document (getElementById "app")))

(defonce init
  (do (fetch-current-month-media!)))

(defn on-js-reload [])


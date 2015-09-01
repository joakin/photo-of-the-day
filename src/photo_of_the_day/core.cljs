(ns ^:figwheel-always photo-of-the-day.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent]
            [cljs.core.async :refer [<!]]
            [cljs.pprint :refer [pprint]]
            [photo-of-the-day.api :as api]
            [photo-of-the-day.state :refer [app-state]]
            [photo-of-the-day.ui.app :refer [app]]
            ))

(enable-console-print!)

(reagent/render-component
  [app]
  (. js/document (getElementById "app")))

(defn on-js-reload [])


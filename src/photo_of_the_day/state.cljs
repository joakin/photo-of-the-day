(ns photo-of-the-day.state
  (:require [reagent.core :as reagent :refer [atom]]))

(defonce app-state
  (atom {:current-month nil}))

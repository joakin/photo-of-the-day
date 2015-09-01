(ns photo-of-the-day.actions
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<!]]
            [cljs.pprint :refer [pprint]]
            [photo-of-the-day.api :as api]
            [photo-of-the-day.state :refer [app-state]]
            ))

(defn- month->str [month]
  (let [s (str month) pad? (= 1 (count s))]
    (if pad? (str "0" s) s)))

(defn fetch-current-month-media! []
  (go
    (let [now (js/Date.)
          year (+ 1900 (.getYear now))
          month (month->str (inc (.getMonth now)))
          {:keys [success body] :as res}
          (<! (api/get-potd! [year month]))]
      (when success
        (swap! app-state assoc :current-month body)))))

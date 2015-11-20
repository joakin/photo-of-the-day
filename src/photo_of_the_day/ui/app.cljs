(ns photo-of-the-day.ui.app)

(defn separator []
  [:div.Separator "&"])

(defn media [{:keys [obj]}]
  [:a.Media {:href (:url obj) :target "_blank"}
   [:div.Media-image [:img {:src (get-in obj [:thumbnail :src])}]]
   [:div.Media-data
    [:p.Media-description (:description obj)]
    ]])

(defn app [state]
  (.log js/console (clj->js @state))
  (let [{:keys [current-month]} @state]
    [:div.App
     [:h1.App-title "Wikipedia Commons"]
     [:h4.App-subtitle "Photos of the month"]
     [:div.App-photos
      (for [img current-month #_(filter #(= (:type %) :bitmap) current-month)]
        [:div {:key (:name img)}
         [media {:obj img}]
         ])]]))

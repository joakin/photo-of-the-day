(ns photo-of-the-day.ui.app)

(defn loader []
  [:div.sk-cube-grid.Loader
   (for [i (range 1 10)]
    [:div {:className (str "sk-cube sk-cube" i)}])
    ])


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
      (if-not false
        [loader]
        (for [img current-month #_(filter #(= (:type %) :bitmap) current-month)]
          [:div {:key (:name img)}
           [media {:obj img}]
           ]))]]))

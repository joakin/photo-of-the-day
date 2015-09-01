(ns photo-of-the-day.ui.app)

(defn app [state]
  (let [{:keys [current-month]} @state]
    [:div
     [:h1 "Photo of the day"]
     (for [img (filter #(= (:type %) :bitmap) current-month)]
       [:div {:key (:name img)}
        [:h4 (:name img)]
        [:img {:src (get-in img [:thumbnail :src])}]
        [:p (:description img)]])]))

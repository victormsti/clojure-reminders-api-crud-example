(ns reminders.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :as json]
            [ring.util.response :refer [response]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [reminders.query :refer :all]))

(defroutes app-routes
(GET "/api/reminders" []
  (response (get-reminders)))
(GET "/api/reminders/:id" [id]
  (response (get-reminder (Integer/parseInt id))))
(POST "/api/reminder" {:keys [params]}
  (let [{:keys [title description]} params]
    (response (add-reminder title description))))
(PUT "/api/reminders/:id" [id title is_complete]
  (response (update-reminder (Integer/parseInt id) title is_complete)))
(DELETE "/api/reminders/:id" [id]
  (response (delete-reminder (Integer/parseInt id))))
(route/resources "/")
(route/not-found "Not Found"))

;(def app
;  (wrap-defaults app-routes site-defaults)
;  )
(def app
  (-> (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false))
      (json/wrap-json-params)
      (json/wrap-json-response {:pretty true})))
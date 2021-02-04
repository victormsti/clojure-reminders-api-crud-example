(ns reminders.query
  (:require [reminders.database]
            [korma.core :refer :all]))

(defentity reminders)

(defn get-reminders []
  (select reminders))

(defn add-reminder [title description]
  (insert reminders
          (values {:title title :description description})))

(defn delete-reminder [id]
  (delete reminders
          (where {:id [= id]})))

(defn update-reminder [id title is-complete]
  (update reminders
          (set-fields {:title title
                       :is_complete is-complete})
          (where {:id [= id]})))

(defn get-reminder [id]
  (first
    (select reminders
            (where {:id [= id]}))))
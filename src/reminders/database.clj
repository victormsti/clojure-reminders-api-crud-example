(ns reminders.database
  (:use [korma.core]
        [korma.db]))

;Put your credentials here
(defdb pg (postgres
            {:host "localhost"
             :port "5432"
             :db "reminders"
             :user "postgres"
             :password "123456"
             :delimiters ""}))
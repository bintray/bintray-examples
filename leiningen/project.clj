;; Build a basic Maven project, and deploy it to Bintray. Use 'lein deploy'.
(defproject cloj-bintray "1.0.0"
  ;; ...
  :deploy-repositories
  [["releases"
    {:url "https://api.bintray.com/maven/:subject/:repo/:package/"
     :sign-releases false
     :username "<bintray_user>"
     :password "<bintray_api_key>}]
   ["snapshots"
    {:url "https://api.bintray.com/maven/:subject/:repo/:package/"
     :username "<bintray_user>"
     :password "<bintray_api_key>}]]
  ;; ...
  :profiles {:dev {:source-paths ["dev"]}})

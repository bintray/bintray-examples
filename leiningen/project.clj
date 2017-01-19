;; Build a basic Maven project, and deploy it to Bintray. Use 'lein deploy'.
(defproject cloj-bintray "1.0.0"
  ;; ...
  :deploy-repositories
 [
   ["releases"
    {:url "https://api.bintray.com/maven/:subject/:repo/:package"
     :sign-releases false
     :username "username"
     :password "apiKey"
    }
   ]
   ["snapshots"
    {:url "https://api.bintray.com/maven/:subject/:repo/:package"
     :username "username"
     :password "apiKey"
    }
   ]
 ]
  ;; ...
  :profiles {:dev {:source-paths ["dev"]}})

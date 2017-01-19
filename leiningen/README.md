## Build a basic Clojure project and deploy it to your Bintray repo

This requires Leiningen installed. Please refer to [this link](http://leiningen.org/#install) for step-by-step installation instructions.

**Your package also needs to exist on Bintray for this to work, so make sure to create it before running 'lein deploy'**

After installing Leiningen, cd into your project.clj directory and run 'lein deploy'. This will build this project sample and deploy it to your Maven repo on Bintray.

Please make sure to replace the username and API key with the placeholders inside the project file with your own.

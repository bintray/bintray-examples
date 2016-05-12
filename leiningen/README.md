## Build a basic Clojure project and deploy it to your Bintray repo

### Bintray setup

Make sure that you add a new package to your Maven repository in Bintray.

### Leiningen setup

This requires Leiningen installed. Please refer to [this link](http://leiningen.org/#install) for step-by-step installation instructions.

After installing Leiningen, cd into your project.clj directory and run `lein deploy`.
This will build this project sample and deploy it to your Maven repo on Bintray.

This example assumes that you have the following environment variables set:

- `BINTRAY_USERNAME`
- `BINTRAY_API_KEY`

You can append `;publish=1` to automatically publish your packages after `lein deploy`.

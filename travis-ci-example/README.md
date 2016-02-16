The full Travis-CI Bintray Deployment documentaion is available [here](https://docs.travis-ci.com/user/deployment/bintray)

This is a basic Maven project that is deployed to Bintray using the Bintray provider of the [dpl] (https://github.com/travis-ci/dpl#bintray)

It first builds the project by running 'mvn install' and uses the .travis.yml descriptor file to trigger the Bintray deployment using the dpl:

```
language: java
script: mvn install
deploy:
  provider: bintray
  file: "Path to a descriptor file, containing information for the Bintray upload"
  user: "Bintray user"
  key: "Bintray API key"
  passphrase: "Optional. In case a passphrase is configured on Bintray and GPG signing is used"
  dry-run: "Optional. If true, skips sending requests to Bintray. Useful for testing your configuration"
  
```

**DO NOT expose your Bintray API Key. Please use the Travis CLI to encrypt your API Key, as explained [here] (https://docs.travis-ci.com/user/deployment/bintray#Encrypt-your-API-key).**
  

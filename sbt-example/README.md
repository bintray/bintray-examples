#### SBT Example
This is a sample project that resolve a dependency from jcenter and deploys the build artifacts to your Bintray repository.
Please make sure to edit the 'publishTo' URL in build.sbt by replacing the ':subject' with your Bintray user name, the ':repo' with your repository name, the ':package' with your Bintray package name and the ':version' with your version.
Also, edit the credentials.properties file to configure your Bintray user name and API KEY.

#### Running this example
```console
> sbt clean compile package publish
```
## Gradle Bintray Plugin Examples
Sample projects that use the Gradle Bintray Plugin.
The plugin adds the "bintrayUpload" task.

#### Running the examples
```console
> gradle build bintrayUpload

or with the gradle wrapper in Unix

> ./gradlew build bintrayUpload

and the gradle wrapper in Windows

> gradlew.bat build bintrayUpload
```

### configurations-example
Gradle 2.x sample project with one module only that uses the archives Configuration by applying the java plugin.
It adds the bintray closure with the mandatory bintray configuration parameters as well as pkg and version configuration to upload artifacts into Bintray.

### configurations-modules-example
Gradle 2.x sample project with a few modules that uses the archives Configuration by applying the java plugin.
It adds the bintray closure with the mandatory bintray configuration parameters as well as pkg and version configuration to upload artifacts into Bintray.

### publications-example
Gradle 2.x sample project with one module that adds maven Publications to the grale script.
It adds the publications defined into the bintray closure with the mandatory bintray configuration parameters as well as pkg and version configuration to upload artifacts into Bintray.

### filesSpec-example
Gradle 2.x sample project with a few modules that uploads arbitrary files from a specific folder to bintray using filesSpec.

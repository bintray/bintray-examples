## Gradle Bintray Plugin Examples
This directory contains example projects that demonstrate the usage of the Gradle Bintray Plugin.
The Gradle Bintray Plugin allows you to publish artifacts to Bintray.
You can read the plugin documentation [here](https://github.com/bintray/gradle-bintray-plugin/blob/master/README.md).

## The example projects

### configurations-example
Gradle project with one module only that uses the archives Configuration by applying the java plugin.
It adds the bintray closure with the mandatory bintray configuration parameters as well as pkg and version configuration to upload artifacts into Bintray.

### configurations-modules-example
Gradle project with a few modules that uses the archives Configuration by applying the java plugin.
It adds the bintray closure with the mandatory bintray configuration parameters as well as pkg and version configuration to upload artifacts into Bintray.

### publications-example
Gradle project with one module that adds maven Publications to the gradle script.
It adds the publications defined into the bintray closure with the mandatory bintray configuration parameters as well as pkg and version configuration to upload artifacts into Bintray.

### filesSpec-example
Gradle project with a few modules that uploads arbitrary files from a specific folder to bintray using filesSpec.

### android-maven-example
Gradle project with one module, that builds aar and pom artifacts using the android-maven Gradle plugin
and uploads them to Bintray.
Before running this example, please make sure you:
* Set the *ANDROID_HOME* environment variable to point to the Android SDK directory.
* Configure the *android* closure in the build.gradle file with your Android SDK versions according to the instructions in the [Android Developers Guide](https://developer.android.com/tools/building/configuring-gradle.html).

## Running the example projects
To run one of the above example projects, please follow the following steps.

#### Step 1:
[Sign up](https://bintray.com/docs/usermanual/working/working_allaboutjoiningbintraysigningupandloggingin.html) to [Bintray](https://bintray.com/).

#### Step 2:
Make sure you have a repository named *generic*.

#### Step 3:
Locate your API Key under Edit Your Profile -> API Key

#### Step 4:
Configure your example project to use your Bintray user and API key.
You can do that using one of the following methods:
* In the project's *gradle.properties* file, set the values of the *bintrayUser* and *bintrayApiKey* properties to your Bintray user and API key.
* Alternatively, you can set the values of the *BINTRAY_USER* and *BINTRAY_API_KEY* environment variables to your Bintray user and API key.
* Another option is to send your Bintray user and API key as project properties when running your build from the command line:
 ```console
 > gradle build bintrayUpload -PbintrayUser=<my user> -PbintrayApiKey=<my key>
```

#### Step 5:
CD to the project's root directory (the directory which includes the *build.gradle* file) and run the build as follows:
```console
> gradle build bintrayUpload

or with the gradle wrapper in Unix

> ./gradlew build bintrayUpload

and the gradle wrapper in Windows

> gradlew.bat build bintrayUpload
```

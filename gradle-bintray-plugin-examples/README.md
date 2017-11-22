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
Gradle project with one module that adds maven Publications to the grale script.
It adds the publications defined into the bintray closure with the mandatory bintray configuration parameters as well as pkg and version configuration to upload artifacts into Bintray.

### filesSpec-example
Gradle project with a few modules that uploads arbitrary files from a specific folder to bintray using filesSpec.

### android-maven-example
Gradle project with one module, that builds aar and pom artifacts using the android-maven Gradle plugin and uploads them to Bintray.

Before running this example, please make sure you:

* Set the *ANDROID_HOME* environment variable to point to the Android SDK directory.

* Configure the *android* closure in the build.gradle file with your Android SDK versions according to the instructions in the [Android Developers Guide](https://developer.android.com/tools/building/configuring-gradle.html).

###  android-gradle-3.0.0-maven-example
Android project with a single library module with some dependencies, using `com.android.tools.build:gradle:3.0.0` with the `implementation` dependency declaration.

To show an example of organising gradle logic, all of the relevant maven/bintray configuration is in `app/publish.gradle`.

Before running this example, please make sure you:

* Set the ANDROID_HOME environment variable to point to the Android SDK directory.

## Running the example projects
To run one of the above example projects, please follow the following steps.

#### Step 1:
[Sign up](https://bintray.com/docs/usermanual/working/working_allaboutjoiningbintraysigningupandloggingin.html) to [Bintray](https://bintray.com/).

#### Step 2:
Make sure you have a repository named *generic* or *maven*, depending on the example you're running (found in `bintray.pkg.repo`).

#### Step 3:
Locate your API Key under Edit Your Profile > API Key

#### Step 4:
Configure your example project to use your Bintray user and API key.
You can do that by using one of the following methods:

* In the project's *gradle.properties* file, set the values of the *bintrayUser* and *bintrayApiKey* properties to your Bintray user and API key.

* Alternatively, you can set the values of the *BINTRAY_USER* and *BINTRAY\_API\_KEY* environment variables to your Bintray user and API key.

* Another option is to send your Bintray user and API key as project properties when running your build from the command line:

 `gradle build bintrayUpload -PbintrayUser=<my user> -PbintrayApiKey=<my key>`

#### Step 5:

Navigate to the project's root directory (the directory which includes the top level `build.gradle` file) and run the build as follows:

`gradle build bintrayUpload --info`

or with the gradle wrapper in Unix

`./gradlew build bintrayUpload --info`

and the gradle wrapper in Windows

`gradlew.bat build bintrayUpload --info`
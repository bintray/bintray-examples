name := "sbt-publish-example"

version := "0.1.0"

description := "Example of an sbt build using Bintray.com."

scalaVersion := "2.10.2"

licenses += ( "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt") )

homepage := Some(url("https://github.com/evantill/bintray-examples"))

scalacOptions ++= List(
  "-encoding", "utf8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-target:jvm-1.6",
  "-language:_"
)

libraryDependencies ++= Seq(
  "org.scala-lang"  %   "scala-reflect"               % scalaVersion.value          ,
  "org.scalatest"   %%  "scalatest"                   % "1.9.2"             % "test",
  "org.scalamock"   %%  "scalamock-scalatest-support" % "3.0.1"             % "test"
)

seq(bintrayPublishSettings:_*)

bintray.Keys.packageLabels in bintray.Keys.bintray := Seq("scala", "bintray", "sbt", "example")


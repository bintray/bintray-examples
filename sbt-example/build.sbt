lazy val root = (project in file(".")).
  settings(
    name := "SBT_Example",
    version := "1.2",
    scalaVersion := "2.11.4"
  )
libraryDependencies ++= Seq(
    "org.apache.derby" % "derby" % "10.4.1.3",
    "org.slf4j" % "slf4j-nop" % "1.7.7")

  resolvers += Resolver.jcenterRepo

  publishTo := Some("Bintray API Realm" at "https://api.bintray.com/content/:subject/:repo/:package/:version")

  credentials += Credentials(new File("credentials.properties"))


lazy val wordRacer = (project in file("."))
  .settings(
    name := "wordracer-scala",
    version := "1.0.0-SNAPSHOT",
    scalaVersion := "2.11.11",
    libraryDependencies ++= Seq(scalatest, junit),
    mainClass in assembly := Some("com.codingnirvana.wordracer.Runner"),
    assemblyJarName in assembly := "wordracer-scala-my-name.jar" // Replace this with a jar name of your choice
  )

val scalatest = "org.scalatest" %% "scalatest" % "3.0.3" % Test
val junit = "junit" % "junit" % "4.11" % Test
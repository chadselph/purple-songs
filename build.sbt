name := "purple-songs"

version := "1.0"

scalaVersion := "2.12.2"


libraryDependencies ++= Seq(
  "org.jsoup" % "jsoup" % "1.10.2",
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "io.circe" %% "circe-core" % "0.7.1",
  "com.itv.chuckwagon" %% "chuckwagon-jvm" % "0.1.0"
)

enablePlugins(ChuckwagonPublishPlugin)
chuckRegion := "us-west-1"
chuckPublishConfig := chuckPublishConfigBuilder
  .withName("purple-songs")
  .withHandler("me.chadrs.karaoke.Runner::handleRequest")
  .withMemorySizeInMB(192)
  .withTimeout("15 seconds")
  .withStagingBucketName("chad-lambda-jars")
  .withCodeFile(assembly)
  .withRoleARN("arn:aws:iam::978490134450:role/purple-songs")

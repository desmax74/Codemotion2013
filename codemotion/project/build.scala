import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object CodemotionBuild extends Build {
  val Organization = "com.codemotion"
  val Name = "codemotion"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.9.2"
  val ScalatraVersion = "2.2.0"

  lazy val project = Project (
    "codemotion",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
      resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
      resolvers += "Akka Repo" at "http://repo.akka.io/repository",

      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-auth" % ScalatraVersion,
        //"org.scalatra" %% "scalatra-data-binding" % "2.3.0-SNAPSHOT",
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "org.json4s"   %% "json4s-jackson" % "3.0.0",
        //"org.scalatra" % "scalatra-json" % "2.2.0-SNAPSHOT",
        "ch.qos.logback" % "logback-classic" % "1.0.9" % "runtime",
        "net.databinder.dispatch" %% "dispatch-core" % "0.9.5",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq.empty,  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}

val CommonSettings = Def.settings(
  organization := "jp.seraphr",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.8"
)

val tProjects = ReflectUtilities.allVals[Project](this).values.toSeq

// root project
lazy val root = (project in file("."))
  .settings(CommonSettings: _*)
  .copy(
    aggregate = tProjects.filterNot(_.id == "root").map(p => p: ProjectReference)
  )

// sub projects
lazy val sub1 = (project in file("sub1")).settings(CommonSettings: _*)
lazy val sub2 = (project in file("sub2")).settings(CommonSettings: _*).dependsOn(sub3)
lazy val sub3 = (project in file("sub3")).settings(CommonSettings: _*)

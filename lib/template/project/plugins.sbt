sbtResolver <<= (sbtResolver) { r =>
  Option(System.getenv("SBT_PROXY_REPO")) map { x =>
    Resolver.url("proxy repo for sbt", url(x))(Resolver.ivyStylePatterns)
    r
  } getOrElse r
}

resolvers <<= (resolvers) { r =>
  Seq(
    "typesafe-twittercache" at "http://typesafe.artifactoryonline.com/typesafe/twitter",
//  "twitter.com" at "http://maven.twttr.com/",
    "scala-tools" at "http://scala-tools.org/repo-releases/",
    "maven" at "http://repo1.maven.org/maven2/"
//  "freemarker" at "http://freemarker.sourceforge.net/maven2/"
  ) ++ (Option(System.getenv("SBT_PROXY_REPO")) map { url =>
    Seq("proxy-repo" at url)
  } getOrElse {
    println("you can set SBT_PROXY_REPO to your personal repo")
    Seq()
  }) ++ Seq("local" at ("file:" + System.getProperty("user.home") + "/.m2/repository/"))
}

externalResolvers <<= (resolvers) map identity

addSbtPlugin("com.twitter" %% "sbt-package-dist" % "1.0.5")

addSbtPlugin("com.twitter" %% "sbt11-scrooge" % "3.0.0")

addSbtPlugin("com.twitter" % "sbt-thrift2" % "0.0.1")

libraryDependencies += "com.twitter" %% "scalatest-mixins" % "1.0.3"

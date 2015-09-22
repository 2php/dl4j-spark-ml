// Your sbt build file. Guides on how to write one can be found at
// http://www.scala-sbt.org/0.13/docs/index.html
name := "dl4j-spark-ml"
organization := "deeplearning4j"
version := "0.4-rc4.2-SNAPSHOT"

// Scala
scalaVersion := "2.10.4"
crossPaths := false  // suppress the scala version number suffix on artifacts

// Resolvers
resolvers += Resolver.mavenLocal

val imageIoVersion = "3.1.1"
val nd4jVersion = "0.4-rc4.2-SNAPSHOT"
val twelveMonkeysGroup = "com.twelvemonkeys.imageio"

val canovaVersion = "0.0.0.9-SNAPSHOT"
val nd4jGroup = "org.nd4j"

// DL4J
libraryDependencies ++= Seq(
  "org.deeplearning4j" % "dl4j-spark-ml" % nd4jVersion,
  twelveMonkeysGroup % "imageio-jpeg" % imageIoVersion,
  twelveMonkeysGroup % "imageio-tiff" % imageIoVersion,
  twelveMonkeysGroup % "imageio-psd" % imageIoVersion,
  twelveMonkeysGroup % "imageio-bmp" % imageIoVersion,
  nd4jGroup % "nd4j-x86" % nd4jVersion exclude("com.github.fommil.netlib", "all"),
  nd4jGroup  % "nd4j-api" % nd4jVersion exclude("com.github.fommil.netlib", "all"),
  nd4jGroup % "nd4j-bytebuddy" % nd4jVersion exclude("com.github.fommil.netlib", "all")

)

// Spark Packaging
credentials += Credentials(Path.userHome / ".ivy2" / ".spcredentials")
sparkVersion := "1.4.0"
spName := "deeplearning4j/dl4j-spark-ml"
spIncludeMaven := false
licenses := Seq("Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0"))

// Add Spark components this package depends on, e.g, "mllib", ....
sparkComponents ++= Seq("sql", "mllib")

// uncomment and change the value below to change the directory where your zip artifact will be created
// spDistDirectory := target.value

// add any Spark Package dependencies using spDependencies.
//spDependencies += "databricks/spark-avro:1.0.0"

spShortDescription := "Deep Learning for Spark ML"

spDescription := """Based on the deeplearning4j library, dl4j-spark-ml provides distributed deep-learning algorithms for
                    classification and reconstruction with Spark ML.   Also provides DataFrame readers for MNIST, 
                    Labeled Faces in the Wild (LFW) and IRIS.
                 """.stripMargin

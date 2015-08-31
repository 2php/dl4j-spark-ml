# Spark Package - dl4j-spark-ml

## Introduction
Welcome to the dl4j-spark-ml project!   dl4j-spark-ml is a Spark Package for the deeplearning4j library.  The package is published to spark-packages.org ([here](http://spark-packages.org/package/deeplearning4j/dl4j-spark-ml)).

The deeplearning4j library provides the following types of neural nets:
 - Restricted Boltzmann machines
 - Convolutional Nets (images)
 - Recurrent Nets/LSTMs (time series and sensor data)
 - Recursive autoencoders
 - Deep-belief networks
 - Deep Autoencoders (QA/data compression)
 - Recursive Neural Tensor Networks (scenes, parsing)
 - Stacked Denoising Autoencoders

The library provides the following Spark components:
 - DataFrame Readers
   - MNIST
   - Labeled Faces in the Wild (LFW)
   - IRIS
 - Pipeline Components
   - NeuralNetworkClassification
   - NeuralNetworkReconstruction

See the [README](https://github.com/deeplearning4j/deeplearning4j/blob/master/deeplearning4j-scaleout/spark/dl4j-spark-ml/README.md) file in deeplearning4j's repository for more details.

### Goals
- Interactive use of deeplearning4j in Spark Shell and Spark Notebook
- Full integration with Spark ML pipeline
- Interoperability with data sources and other pipeline components
```
> $SPARK_HOME/bin/spark-shell --packages deeplearning4j:dl4j-spark-ml:0.4-rc0
```

### Example Code
See the example code in the [dl4j-spark-ml-examples](https://github.com/deeplearning4j/dl4j-spark-ml-examples) repository.

## Publishing
_This section describes how to to publish the Spark Package._

The project uses the [sbt-spark-package](https://github.com/databricks/sbt-spark-package) plugin, which provides the 'spPublish' task.

### Create credentials cache
Follow the [instructions](https://github.com/databricks/sbt-spark-package/blob/master/README.md#registering-and-publishing-spark-packages) provided by the sbt-spark-package plugin.   Your credentials file should be located at `~/.ivy2/.spcredentials`, and resemble:
```
realm=Spark Packages Realm
host=spark-packages.org
user=(Github Username)
password=(Github Personal Access Token)
```

### Update version info
Update the version information in `build.sbt`.  Update dependencies as necessary.

### Register
_This task is performed once to register the package at spark-packages.org.   It (probably) may be used to update the package description._
Run the `spRegister` task.

### Publish
Run the `spPublish` task.
```
$ sbt
[info] Loading project definition from /.../dl4j-spark-ml/project
[info] Set current project to dl4j-spark-ml (in build file:/.../dl4j-spark-ml/)
> spPublish
```

## Developing
### Background
A Spark Package is a Maven artifact published to a Maven repository like normal.  The associated JAR file is *not* an uber-jar; Spark automatically downloads the dependencies as needed.

The Spark Packages website is a repository that compliments Maven Central with different publishing rules and procedures.  The main difference is in how provenance is handled.  Your artifact's coordinates must exactly match the code's corresponding github location.  

Of course, your artifact's dependencies may originate from any repository known to spark-submit.

### Working with Snapshots
It is not possible to publish a package that refers to unpublished artifacts.  To make use of a dependency in your local repository (such as `org.deeplearning4j:dl4j-spark-ml`), update the build script to use the dependency, then publish the package to the local Maven repository.

### Publish to Local Maven Repository
The build script is designed to support both the 'publishM2' and the 'spPublishLocal' tasks, to publish to the local-maven-cache and local-ivy-cache respectively.   The spark-submit tool gladly looks to both repositories, but mishandles the ivy case due to SPARK-8095.   Use local-maven-cache for now.

### Clear the ivy cache
Keep in mind that the dl4j build script publishes only to local-maven-cache; the local-ivy-cache is not kept in sync.  Delete `~/.ivy2/cache/org.deeplearning4j` and similar as necessary.

### Troubleshooting
Some relevant snippets:
- [spark/core/src/main/scala/org/apache/spark/deploy/SparkSubmit.scala::resolveMavenCoordinates](https://github.com/apache/spark/blob/branch-1.4/core/src/main/scala/org/apache/spark/deploy/SparkSubmit.scala#L914)
- [sbt-spark-package/src/main/scala/sbtsparkpackage/SparkPackagePlugin.scala](https://github.com/databricks/sbt-spark-package/blob/master/src/main/scala/sbtsparkpackage/SparkPackagePlugin.scala)

### More Information
Background information on Spark Packages:
- [Announcing Spark Packages](https://databricks.com/blog/2014/12/22/announcing-spark-packages.html)
- [Publishing Instructions](http://spark-packages.org/artifact-help)

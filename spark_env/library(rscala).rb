library(rscala)
spark_jars <- dir("/root/.sdkman/candidates/spark/2.4.7/jars/", full.names = TRUE)
engine = rscala::scala(spark_jars)
engine + "import org.apache.spark.sql.SparkSession"
engine + 'val spark2 = SparkSession.builder.appName("Simple Application").getOrCreate()'


engine + 'val spark2 = SparkSession.builder.appName("Simple Application").get()'


engine + 'import spark.implicits._'
engine + 'val columns = Seq("language","users_count")'
engine + 'val data = Seq(("Java", "20000"), ("Python", "100000"), ("Scala", "3000"))'
engine + 'val rdd = spark.sparkContext.parallelize(data)'

library(rscala)
engine = rscala::scala(character())
engine + "import org.apache.spark.sql.SparkSession"
engine + 'val spark2 = SparkSession.builder.master("local[1]").appName("Simple Application").getOrCreate()'





jars <- dir("/root/.sdkman/candidates/spark/2.4.7/jars/", full.names = TRUE)

jarsToRemove <- c("scala-compiler-.*\\.jar$",
                  "scala-library-.*\\.jar$",
                  "scala-reflect-.*\\.jar$",
                  "scalap-.*\\.jar$",
                  "scala-parser-combinators_.*\\.jar$",
                  "scala-xml_.*\\.jar$")
jars <- jars[!grepl(jars, pattern = paste(jarsToRemove, collapse = "|"))]

engine = rscala::scala(jars)
engine + "import org.apache.spark.sql.SparkSession"
engine + 'val spark = SparkSession.builder.master("local[1]").appName("Simple Application").getOrCreate()'


engine + 'SparkSession.builder().appName("Spark SQL basic example").getOrCreate()'

engine * 'util.Properties.versionString'
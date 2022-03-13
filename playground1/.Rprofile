print("******Init from .Rprofile******")

library(rscala)
library(knitr)

make_scala_engine <- function(jars, ...) {
  
  engine = rscala::scala(jars, serialize.output = TRUE, stdout = "", ...)
  engine <- force(engine)
  function(options) {
    code <- paste(options$code, collapse = "\n")
    output <- capture.output(invisible(engine + code))
    engine_output(options, options$code, output)
  }
}

# Register new engine in knitr
scala_jars = character()
knit_engines$set(scalar = make_scala_engine(scala_jars))





jars <- dir("/root/.sdkman/candidates/spark/2.4.7/jars/", full.names = TRUE)

jarsToRemove <- c("scala-compiler-.*\\.jar$",
                  "scala-library-.*\\.jar$",
                  "scala-reflect-.*\\.jar$",
                  "scalap-.*\\.jar$",
                  "scala-parser-combinators_.*\\.jar$",
                  "scala-xml_.*\\.jar$")
spark_jars <- jars[!grepl(jars, pattern = paste(jarsToRemove, collapse = "|"))]

#engine = rscala::scala(jars)


#spark_jars <- dir("/root/.sdkman/candidates/spark/2.4.7/jars/", full.names = TRUE)

knit_engines$set(spark = make_scala_engine(spark_jars))


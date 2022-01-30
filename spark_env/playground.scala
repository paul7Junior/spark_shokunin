val path_entities = "/srv/full-oldb-20220110/nodes-entities.csv"
val df = spark.read.format("csv").option("header", true).load(path)

val path_officers = "/srv/full-oldb-20220110/nodes-officers.csv"
val officers = spark.read.format("csv").option("header", true).load(path_officers)

val path_intermediaris = "/srv/full-oldb-20220110/nodes-intermediaries.csv"
val intermediaries = spark.read.format("csv").option("header", true).load(path_intermediaris)


val f=intermediaries.withColumn("country", split($"countries", ";")).withColumn("country", explode_outer($"country"))


intermediaries.filter($"countries".isNull).count

val numPattern = "".r
val address = "123 Main Street Suite 101"
numPattern.findFirstIn(address)
val a = df.select($"name").map(x=>numPattern.findFirstIn(x.getString(0)))


"TRH", "HRH", "H R H ","H.R.H", "Prince", "prince", "princess", "sultan"


val royalPattern = "HRH".r
val sample = df.limit(10)

val a = df.select($"name").map(x=>royalPattern.findFirstIn(x.getString(0)).getOrElse(""))


a.filter($"value".isNotNull).filter($"value" !== "").show(100)


df.filter($"name".rlike("(HRH)")).show

df.filter($"name".rlike("TRH")).show
df.filter($"name".rlike("Lord")).show
df.filter($"name".rlike("Prince")).show

df.filter($"name".rlike("DATION")).show

df.filter($"name".rlike("FOUNDATION")).show


df.filter($"name".rlike("prince")).show
intermediaries.filter($"name".rlike("prince")).show
officers.filter($"name".rlike("Prince")).show
officers.filter($"name".rlike("HRH")).count

intermediaries.filter($"name".rlike("charity")).show
officers.filter($"name".rlike("charity")).show
df.filter($"name".rlike("charity")).show


intermediaries.filter($"name".rlike("FOUNDATION")).show
officers.filter($"name".rlike("FOUNDATION")).show
df.filter($"name".rlike("FOUNDATION")).show

df.select($"name")
val a = df.withColumn("aa", split($"name", " "))

val b = a.select(explode($"aa"))
val n = b.groupBy($"col").count.sort(desc("count"))

n.show(500, false)

n.count

df.filter($"name".rlike("*HRH*")).show



filter($"name")

a.select($"value").distinct.show
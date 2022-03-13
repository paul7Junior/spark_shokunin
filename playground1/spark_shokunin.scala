// type class: 3 level of understanding, not loosing the big picture, what is the utility/ purpose of each block>?


// Create dataframes

case class Test(name: String, age: Int)

case class withMap(name: String, age: Int, themap: Map[String, Int])

case class withArray(name: String, age: Int, arrayField: Array[String])

case class withArray(name: String, age: Int, arrayField: Array[String])

MapType(StringType,StringType)


val a = Seq.range(1,10).map(withArray("Anto", _, Array("dd", "fff")))

val b = Seq.range(1,10).map(withMap("Anto", _, Map("dd" -> 3, "ffff" -> 5)))
b.toDF.withColumn("d"::"ff"::Nil, explode($"themap")).show

b.toDF.select(explode($"themap")).show


b.toDF.select("name")


a.toDF.select($"arrayField", reverse($"arrayField") as "reversed").show

import org.apache.spark.sql.Row


b.toDF.select("name").reduce((a,b)=> Row(a.getString(0)+ "\",\"" + b.getString(0)))
b.toDF.select("name").reduce((a,b)=> Row(a(0)+ "\",\"" + b(0)))

b.toDF.selectExpr("*", "explode(themap) as (p,c)").show


b.toDF.select(posexplode($"themap")).show

b.toDF.select(posexplode($"themap")).show



Array(1, 2, 3)

val a:Array[Int] = Array(1,2,3,4)


def randomSeq[A](a:A):Seq[A] = {
    return Seq(a)
}


val f = a.toDF

f.select($"age", explode($"arrayField"))


f.withColumn("d", explode($"arrayField")).show

randomSeq[Int](3)
randomSeq(withArray("Anto", 21, Array("dd", "fff")))

val a = Seq.range(1,10).map(withArray("Anto", _, Array("dd", "fff")))



val EmployeesData = Seq(Test("Anto", 21),Test("Anto", 21))
val employee_DataFrame = EmployeesData.toDF

employee_DataFrame.as[Test]
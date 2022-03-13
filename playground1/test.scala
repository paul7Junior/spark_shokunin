// import $ivy.`org.scalaz::scalaz-core:7.2.27`, scalaz._, Scalaz._
// import $ivy.`org.scalamacros:::paradise:2.1.1`, org.scalamacros.paradise.Settings._
import $ivy.`com.chuusai::shapeless:2.3.3`, shapeless._

// Environment for Spark
// java: 8.0.292.hs-adpt
// sbt: 1.3.13
// scala: 2.11.12
// spark: 2.4.7

trait CsvEncoder[A] {
    def encode(value: A): List[String]
}

case class Employee(name: String, number: Int, manager: Boolean)

// CsvEncoder instance for the custom data type:
implicit val employeeEncoder: CsvEncoder[Employee] =
    new CsvEncoder[Employee] {
    def encode(e: Employee): List[String] =
    List(
    e.name,
    e.number.toString,
    if(e.manager) "yes" else "no"
    )
}

def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
values.map(value => enc.encode(value).mkString(",")).mkString("\n")

val employees: List[Employee] = List(
Employee("Bill", 1, true),
Employee("Peter", 2, false),
Employee("Milton", 3, false)
)

writeCsv(employees)

// type class with another example

case class IceCream(name: String, numCherries: Int, inCone: Boolean)

val iceCream = IceCream("Sundae", 1, false)

implicit val iceCreamEncoder: CsvEncoder[IceCream] =
new CsvEncoder[IceCream] {
def encode(i: IceCream): List[String] =
List(
i.name,
i.numCherries.toString,
if(i.inCone) "yes" else "no"
)
}
val iceCreams: List[IceCream] = List(
IceCream("Sundae", 1, false),
IceCream("Cornetto", 0, true),
IceCream("Banana Split", 0, false)
)

writeCsv(iceCreams)

// Scala compiler approach to resolving instances. 
// For example pairEncoder

implicit def pairEncoder[A, B](implicit aEncoder: CsvEncoder[A], bEncoder: CsvEncoder[B]): CsvEncoder[(A, B)] =
    new CsvEncoder[(A, B)] {
        def encode(pair: (A, B)): List[String] = {
        val (a, b) = pair
        aEncoder.encode(a) ++ bEncoder.encode(b)
        }
}





object CsvEncoder {
    // "Summoner" method
    def apply[A](implicit enc: CsvEncoder[A]): CsvEncoder[A] = enc
    // "Constructor" method
    def instance[A](func: A => List[String]): CsvEncoder[A] =
    new CsvEncoder[A] {
    def encode(value: A): List[String] = func(value)
    }
    // Globally visible type class instances
}









//// Instances for Hlist

// Define the trait
trait CsvEncoder[A] {
    def encode(value: A): List[String]
}

// Define function to instantiate csvEncoder
def createEncoder[A](func: A => List[String]): CsvEncoder[A] =
    new CsvEncoder[A] {
    def encode(value: A): List[String] = func(value)
}

// Instantiate csvEncoder for the different types
implicit val stringEncoder: CsvEncoder[String] = createEncoder(str => List(str))
implicit val intEncoder: CsvEncoder[Int] = createEncoder(num => List(num.toString))
implicit val booleanEncoder: CsvEncoder[Boolean] = createEncoder(bool => List(if(bool) "yes" else "no"))

// Instantitate csvEncoder for HList i.e any combination of types
import shapeless.{HList, ::, HNil}
implicit val hnilEncoder: CsvEncoder[HNil] = createEncoder(hnil => Nil)

implicit def hlistEncoder[H, T <: HList] (
    implicit
    hEncoder: CsvEncoder[H],
    tEncoder: CsvEncoder[T]
    ): CsvEncoder[H :: T] =
    createEncoder {
        case h :: t =>
            hEncoder.encode(h) ++ tEncoder.encode(t)
    }

// resolve implicits
val reprEncoder: CsvEncoder[String :: Int :: Boolean :: HNil] = implicitly

// example
reprEncoder.encode("abc" :: 123 :: true :: HNil)

// **** Instances for concret product

import shapeless.Generic
case class IceCream(name: String, numCherries: Int, inCone: Boolean)

implicit val iceCreamEncoder: CsvEncoder[IceCream] = {
val gen = Generic[IceCream]
val enc = CsvEncoder[String]
// createEncoder(iceCream => enc.encode(gen.to(iceCream)))
}

val repr = gen.to(iceCream)

val tupleGen = Generic[(String, Int, Boolean)]



implicit def genericEncoder[A, R](
implicit
gen: Generic[A] { type Repr = R },
enc: CsvEncoder[R]
): CsvEncoder[A] =
createEncoder(a => enc.encode(gen.to(a)))


def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
values.map(value => enc.encode(value).mkString(",")).mkString("\n")

val iceCreams: List[IceCream] = List(
IceCream("Sundae", 1, false),
IceCream("Cornetto", 0, true),
IceCream("Banana Split", 0, false)
)

writeCsv(iceCreams)(genericEncoder(IceCream, ))


//  Deriving Corproduct
{
sealed trait Shape
final case class Rectangle(width: Double, height: Double) extends Shape
final case class Circle(radius: Double) extends Shape
}

import shapeless.Generic
implicit val iceCreamEncoder: CsvEncoder[IceCream] = {
val gen = Generic[IceCream]
val enc = CsvEncoder[gen.Repr]
createEncoder(iceCream => enc.encode(gen.to(iceCream)))
}


// Dependant types

import shapeless.Generic
def getRepr[A](value: A)(implicit gen: Generic[A]): gen.Repr = gen.to(value)

source(shapeless::Generic)
source(shapeless.::)

trait test {
    type a = Generic[String]
    type b = a.Repr
}

case class Vec(x: Int, y: Int)
case class Rect(origin: Vec, size: Vec)
getRepr(Vec(1, 2))



// Litteral types
import shapeless.syntax.singleton._


import shapeless.labelled.field
field[Cherries](123)

type FieldType[K, V] = V with KeyTag[K, V]

import shapeless.Witness
val numCherries = "numCherries" ->> 123
// numCherries: Int with shapeless.labelled.KeyTag[String("numCherries"),Int] = 123
// Get the tag from a tagged value:
def getFieldName[K, V](value: FieldType[K, V])(implicit witness: Witness.Aux[K]): K = witness.value
getFieldName(numCherries)
def getFieldValue[K, V](value: FieldType[K, V]): V = value
getFieldValue(numCherries)

// Records

import shapeless.{HList, ::, HNil}
val garfield = ("cat" ->> "Garfield") :: ("orange" ->> true) :: HNil
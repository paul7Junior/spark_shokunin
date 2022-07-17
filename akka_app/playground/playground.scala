// Ammonite
val akkaHttpVersion = "10.2.7"
import $ivy. `com.typesafe.akka::akka-parsing:10.2.7`
import $ivy. `com.typesafe.akka::akka-http-core:10.2.7`
import $ivy. `com.typesafe.akka::akka-actor-typed:2.6.19`
import $ivy. `com.typesafe.akka::akka-stream:2.6.19`
import $ivy. `com.typesafe.akka::akka-http:10.2.7`
import $ivy. `ch.megard::akka-http-cors:1.1.3`

// Akka related
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings
import akka.http.scaladsl.model.headers._

import akka.http.scaladsl.server._

import $ivy. `com.sksamuel.elastic4s::elastic4s-client-esjava:8.1.0` 
import $ivy. `com.sksamuel.elastic4s::elastic4s-core:8.1.0` 

import collection.JavaConverters._

// Elastic related
import com.sksamuel.elastic4s.fields.TextField
import com.sksamuel.elastic4s.http.JavaClient
import com.sksamuel.elastic4s.requests.common.RefreshPolicy
import com.sksamuel.elastic4s.requests.searches.SearchResponse
import com.sksamuel.elastic4s.http.{NoOpRequestConfigCallback}
import org.elasticsearch.client.RestClientBuilder.{HttpClientConfigCallback, RequestConfigCallback}
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.apache.http.impl.client.{BasicCredentialsProvider, HttpClientBuilder}
import org.apache.http.auth.{AuthScope, UsernamePasswordCredentials}
import com.sksamuel.elastic4s.{ElasticClient, ElasticProperties, RequestFailure, RequestSuccess, HitReader, Hit}
import com.sksamuel.elastic4s.ElasticDsl._

// circe
import $ivy. `io.circe::circe-core:0.14.1`
import $ivy. `io.circe::circe-generic:0.14.1`
import $ivy. `io.circe::circe-parser:0.14.1`

import io.circe.generic.auto._

// json handling
// import $ivy. `de.heikoseeberger::akka-http-circe:1.39.2`
// import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport

// json support
import $ivy. `com.typesafe.akka::akka-http-spray-json:10.2.7`
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

// scala related
import scala.util.{Success, Try}



// Elastic Connection

val props = ElasticProperties("http://127.0.0.1:9200")
val client = ElasticClient(JavaClient(props, requestConfigCallback = NoOpRequestConfigCallback))

// Akka Server

val settings = CorsSettings("""akka-http-cors {
  allow-generic-http-requests = yes
  allow-credentials = yes
  allowed-origins = "*"
  allowed-headers = "*"
  allowed-methods = ["GET", "POST", "HEAD", "OPTIONS"]
  exposed-headers = []
  max-age = 1800 seconds
}""")


val rejectionHandler = corsRejectionHandler.withFallback(RejectionHandler.default)
val exceptionHandler = ExceptionHandler { case e: NoSuchElementException =>
  complete(StatusCodes.NotFound -> e.getMessage)
}
val handleErrors = handleRejections(rejectionHandler) & handleExceptions(exceptionHandler)

case class Node(path: String, content: String)    
case class Nodes(results:IndexedSeq[Node])





// nodeArray.as[Nodes]

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val nodeFormat: RootJsonFormat[Node] = jsonFormat2(Node)
  implicit val nodesFormat: RootJsonFormat[Nodes] = jsonFormat1(Nodes)
}

case class Query(value: String)
trait QueryJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val formats = jsonFormat1(Query)
}

class MyJsonService extends Directives with JsonSupport with QueryJsonSupport {

    implicit object NodeHitReader extends HitReader[Node] {
    override def read(hit: Hit): Try[Node] = {
        val source = hit.sourceAsMap
        Try(Node(source("path").toString, source("content").toString))
    }
    }

    val route = 
        handleErrors {
            cors(settings) {
            path("search") { 
              entity(as[Query]) { query =>
                                  complete {
                val resp = client.execute {
                    search("node").query(query.value)
                }.await

                val nodeArray = resp.result.to[Node]

                nodeArray
            }
                // complete(s"Query Value: ${query.value}")
            }
            } ~
            path("elastic") {
                complete {
                val resp = client.execute {
                    search("node").query("spark")
                }.await
                val nodeArray = resp.result.to[Node]
                nodeArray
            }
            } ~
            parameters("query") { (queryText) =>
                complete {
                val resp = client.execute {
                    search("node").query(queryText)
                }.await
                val nodeArray = resp.result.to[Node]
                nodeArray
            }
            }
            }
        }
}


val jj = new MyJsonService
implicit val system = ActorSystem(Behaviors.empty, "my-system")
implicit val executionContext = system.executionContext
val bindingFuture = Http().newServerAt("localhost", 8082).bind(jj.route)


bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())



import akka.http.scaladsl.client.RequestBuilding.Post
val response = Post("http://localhost:8082/elastic", "data")

import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import scala.concurrent.Future


val route = 
    handleErrors {
        cors(settings) {
        path("mypath") { 
              entity(as[Person]) { person =>
                complete(s"Person: ${person.name} - favorite number: ${person.favoriteNumber}")
            }
        } ~
        path("ping") {
        complete("pong")
        } ~
        path("pong") {
        failWith(new NoSuchElementException("pong not found, try with ping"))
        } 
     }
    }

implicit val system = ActorSystem(Behaviors.empty, "my-system")
implicit val executionContext = system.executionContext
val bindingFuture = Http().newServerAt("localhost", 8082).bind(route)

bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
















Seq(Node("dd", "ff"))

def queryElastic(): 


Node(f(0).sourceAsMap)

// Testing typesafe

import com.typesafe.config.{ Config, ConfigFactory }
val c:Config = ConfigFactory.parseString("foo: bar, dddd: jjj");
val c:Config = ConfigFactory.parseMap(Map("dd" -> ()=> "ss"));
val c:Config = ConfigFactory.parseMap(Map("dd" -> "ss"));
val a = java.util.Map()
val map = Map("akka-http-cors" -> Map(("allow-generic-http-requests" -> "true")).asJava).asJava
val c:Config = ConfigFactory.parseMap(map);
CorsSettings(c)

val c:Config = ConfigFactory.parseString("""akka-http-cors: {"akka-http-cors": "dfff"}""");



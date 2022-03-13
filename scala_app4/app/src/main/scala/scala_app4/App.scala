package scala_app4

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model._

import scala.util.Failure
import scala.util.Success
import scala.io.StdIn


import com.sksamuel.elastic4s.RefreshPolicy
import com.sksamuel.elastic4s.http.{ElasticClient, ElasticProperties}
import com.sksamuel.elastic4s.http.Response
import com.sksamuel.elastic4s.http.search.SearchResponse

object App {

  def elastic_app() = {
      import com.sksamuel.elastic4s.http.ElasticDsl._

  val client = ElasticClient(ElasticProperties("http://localhost:9200"))

  client.execute {
    bulk(
      indexInto("myindex" / "mytype").fields("country" -> "Mongolia", "capital" -> "Ulaanbaatar"),
      indexInto("myindex" / "mytype").fields("country" -> "Namibia", "capital" -> "Windhoek")
    ).refresh(RefreshPolicy.WaitFor)
  }.await

  val response: Response[SearchResponse] = client.execute {
    search("myindex").matchQuery("capital", "ulaanbaatar")
  }.await


  import $ivy.`com.sksamuel.elastic4s::elastic4s-http:6.7.7`
  import $ivy.`com.sksamuel.elastic4s::elastic4s-jackson:6.7.7`

  import $ivy.`com.sksamuel.elastic4s::elastic4s-client-esjava:7.16.3`

  "com.sksamuel.elastic4s" %% "elastic4s-client-esjava" % elastic4sVersion
  // import $ivy.`com.sksamuel.elastic4s::elastic4s-core:7.16.3`

  import com.sksamuel.elastic4s._
  import com.sksamuel.elastic4s.http.{ElasticClient, ElasticProperties}
  import com.sksamuel.elastic4s.http.{search}
  import com.sksamuel.elastic4s.http.search.SearchResponse
  import com.sksamuel.elastic4s.http.index.admin.IndexExistsResponse
  import com.sksamuel.elastic4s.ElasticDsl._


    val client = ElasticClient(JavaClient(ElasticProperties("http://localhost:9200")))

  // val client = ElasticClient(ElasticProperties("http://localhost:9200"))


  val b = client.execute {
    search("myindex").matchQuery("capital", "ulaanbaatar")
  }.await

    val b = client.execute {
    search("myindex").query("Namibia")
  }

  b match {
    case failure: RequestFailure => println("We failed " + failure.error)
    case results: RequestSuccess[SearchResponse] => println(results.result.hits.hits.toList)
    case results: RequestSuccess[_] => println(results.result)
  }

  client.execute {
    indexExists("myindex")
  }.await match {
    case failure: RequestFailure => println("FAILURE!!! ", failure.error)
    case response: RequestSuccess[IndexExistsResponse] => println("SUCCESS!!!!", response.result.exists)
  } 

  import com.sksamuel.elastic4s.http.search
  val response = client.execute {
    search("myindex").matchQuery("capital", "ulaanbaatar")
  }.await

  // prints out the original json
  println(response.result.hits.hits.head.sourceAsString)

  client.close()
  }

  def main(args: Array[String]): Unit = {

    elastic_app()
  //   implicit val system = ActorSystem(Behaviors.empty, "my-system")
  //   // needed for the future flatMap/onComplete in the end
  //   implicit val executionContext = system.executionContext

  //   val route =
  //     path("hello") {
  //       get {
  //         complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
  //       }
  //     }

  //   val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

  //   println(s"Server now online. Please navigate to http://localhost:8080/hello\nPress RETURN to stop...")
  //   StdIn.readLine() // let it run until user presses return
  //   bindingFuture
  //     .flatMap(_.unbind()) // trigger unbinding from the port
  //     .onComplete(_ => system.terminate()) // and shutdown when done
   }
}


//////////////////////////////////////////////////////////////////////

// object App {
//   //#start-http-server
//   private def startHttpServer(routes: Route)(implicit system: ActorSystem[_]): Unit = {
//     // Akka HTTP still needs a classic ActorSystem to start
//     import system.executionContext

//     val futureBinding = Http().newServerAt("localhost", 8081).bind(routes)
//     futureBinding.onComplete {
//       case Success(binding) =>
//         val address = binding.localAddress
//         system.log.info("Server online at http://{}:{}/", address.getHostString, address.getPort)
//       case Failure(ex) =>
//         system.log.error("Failed to bind HTTP endpoint, terminating system", ex)
//         system.terminate()
//     }
//   }
//   //#start-http-server
//   def main(args: Array[String]): Unit = {

//     //#server-bootstrapping
//     val rootBehavior = Behaviors.setup[Nothing] { context =>

//       val userRegistryActor = context.spawn(UserRegistry(), "UserRegistryActor")
//       context.watch(userRegistryActor)

//       val routes = new UserRoutes(userRegistryActor)(context.system)
//       startHttpServer(routes.userRoutes)(context.system)

//       Behaviors.empty
//     }
//     val system = ActorSystem[Nothing](rootBehavior, "HelloAkkaHttpServer")
//     //#server-bootstrapping
//   }
// }

/// ORIGINAL APP OBJECT ------------------------

// object App {
//   def main(args: Array[String]): Unit = {
//     println(greeting())
//   }

//   def greeting(): String = "Hellofff, wrrrorld!"
// }



// import $ivy.`com.chuusai::shapeless:2.3.3`, shapeless._

// import $ivy.`com.sksamuel.elastic4s::elastic4s-http:6.7.8`
// import $ivy.`com.sksamuel.elastic4s::elastic4s-core:7.17.1`



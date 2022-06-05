/*
 * This Scala source file was generated by the Gradle 'init' task.
 */
package akka_app.app

import akka_app.utilities.StringUtils

import org.apache.commons.text.WordUtils

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import scala.io.StdIn

object App {
   def main(args: Array[String]): Unit = {

        implicit val system = ActorSystem(Behaviors.empty, "my-system")
        // needed for the future flatMap/onComplete in the end
        implicit val executionContext = system.executionContext

        val route =
        path("hello") {
            get {
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
            }
        }

        val bindingFuture = Http().newServerAt("localhost", 8081).bind(route)

        println(s"Server now online. Please navigate to http://localhost:8080/hello\nPress RETURN to stop...")
        StdIn.readLine() // let it run until user presses return
        bindingFuture
        .flatMap(_.unbind()) // trigger unbinding from the port
        .onComplete(_ => system.terminate()) // and shutdown when done

        // val tokens = StringUtils.split(MessageUtils.getMessage())
        // val result = StringUtils.join(tokens)
        // println(WordUtils.capitalize(result))


    }
}
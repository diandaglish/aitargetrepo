package docket

import org.http4k.core.ContentType
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.lens.Header
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

fun docket(): HttpHandler = routes(
    "/health" bind GET to { Response(OK).body("OK") },
    "/greeting" bind GET to { request ->
        val name = request.query("name")
        try {
            val greeting = generateGreeting(name)
            Response(OK)
                .header("Content-Type", "text/plain")
                .body(greeting)
        } catch (e: ValueError) {
            Response(BAD_REQUEST)
                .header("Content-Type", "text/plain")
                .body(e.message ?: "Bad request")
        }
    }
)

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    docket().asServer(Undertow(port)).start().also {
        println("Docket started on http://localhost:$port")
    }.block()
}

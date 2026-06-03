package docket

import org.http4k.core.ContentType
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.lens.Header.CONTENT_TYPE
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

fun docket(greetingService: GreetingService = GreetingService()): HttpHandler = routes(
    "/health" bind GET to { Response(OK).body("OK") },
    "/greet" bind GET to {
        Response(OK)
            .with(CONTENT_TYPE of ContentType.TEXT_PLAIN)
            .body(greetingService.getStaticGreeting())
    }
)

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    docket().asServer(Undertow(port)).start().also {
        println("Docket started on http://localhost:$port")
    }.block()
}

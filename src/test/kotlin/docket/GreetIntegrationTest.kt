package docket

import org.http4k.core.Status.Companion.OK
import org.http4k.format.Jackson
import org.http4k.server.Undertow
import org.http4k.server.asServer
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class GreetIntegrationTest {

    @Test
    fun `test get greet endpoint returns hello world`() {
        val port = 0 // Let the OS assign a free port
        val server = docket().asServer(Undertow(port)).start()
        val assignedPort = server.port()

        try {
            val client = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:$assignedPort/greet"))
                .GET()
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())

            assertEquals(200, response.statusCode())

            val body = Jackson.parse(response.body())
            assertEquals("Hello, World!", body["message"]?.asText())
        } finally {
            server.stop()
        }
    }
}

package docket

import org.http4k.client.JavaHttpClient
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Undertow
import org.http4k.server.asServer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GreetEndpointIntegrationTest {

    @Test
    fun test_greet_endpoint_static_message_integration() {
        val server = docket().asServer(Undertow(0)).start()
        val port = server.port()
        try {
            val client = JavaHttpClient()
            val response = client(Request(GET, "http://localhost:$port/greet"))

            assertEquals(OK, response.status)
            val contentType = response.header("Content-Type")
            assertTrue(contentType != null && contentType.contains("application/json"),
                "Expected Content-Type to contain application/json but was: $contentType")
            assertEquals("""{"message":"Hello, World!"}""", response.bodyString())
        } finally {
            server.stop()
        }
    }
}

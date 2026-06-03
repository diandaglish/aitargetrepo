package docket

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import kotlin.test.Test
import kotlin.test.assertEquals

class GreetTest {

    @Test
    fun test_greet_handler_static_response() {
        val response = greetHandler()
        assertEquals("""{"message":"Hello!"}""", response.bodyString())
    }

    @Test
    fun test_greet_endpoint_static_response() {
        val app = docket()
        val response = app(Request(GET, "/greet"))
        assertEquals(OK, response.status)
        assertEquals("""{"message":"Hello!"}""", response.bodyString())
    }
}

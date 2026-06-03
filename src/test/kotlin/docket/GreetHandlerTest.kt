package docket

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import kotlin.test.Test
import kotlin.test.assertEquals

class GreetHandlerTest {

    @Test
    fun test_greet_handler_static_message() {
        val request = Request(GET, "/greet")
        val response = greetHandler(request)

        assertEquals(OK, response.status)
        assertEquals("""{"message":"Hello, World!"}""", response.bodyString())
    }
}

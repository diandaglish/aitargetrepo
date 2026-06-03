package docket

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.format.Jackson
import kotlin.test.Test
import kotlin.test.assertEquals

class GreetHandlerTest {

    @Test
    fun `test greet handler returns fixed message`() {
        val handler = greetHandler()
        val response = handler(Request(GET, "/greet"))

        assertEquals(OK, response.status)

        val body = Jackson.parse(response.bodyString())
        assertEquals("Hello, World!", body["message"]?.asText())
    }
}

package docket

import org.http4k.core.ContentType
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GreetEndpointTest {

    private val app = docket()

    @Test
    fun `GET greet returns 200 OK with Hello! in plain text`() {
        val response = app(Request(GET, "/greet"))

        assertEquals(OK, response.status)
        assertEquals("Hello!", response.bodyString())
        assertTrue(
            response.header("Content-Type")?.startsWith(ContentType.TEXT_PLAIN.value) == true,
            "Expected Content-Type to be text/plain but was ${response.header("Content-Type")}"
        )
    }
}

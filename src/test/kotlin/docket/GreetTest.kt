package docket

import org.http4k.core.ContentType
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GreetTest {

    private val app = docket()

    @Test
    fun `GET greet returns 200 OK with Hello! in plain text`() {
        val response = app(Request(GET, "/greet"))

        assertEquals(OK, response.status)
        assertEquals("Hello!", response.bodyString())
        val contentType = response.header("Content-Type")
        assertTrue(contentType != null && contentType.startsWith("text/plain"),
            "Expected Content-Type to start with text/plain but was: $contentType")
    }
}

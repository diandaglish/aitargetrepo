package docket

import org.http4k.core.ContentType
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.Header.CONTENT_TYPE
import kotlin.test.Test
import kotlin.test.assertEquals

class GreetIntegrationTest {

    private val app = docket()

    @Test
    fun `GET greet returns 200 with Hello World plain text`() {
        val response = app(Request(GET, "/greet"))

        assertEquals(OK, response.status)
        assertEquals("Hello, World!", response.bodyString())
        assertEquals(ContentType.TEXT_PLAIN, CONTENT_TYPE(response))
    }
}

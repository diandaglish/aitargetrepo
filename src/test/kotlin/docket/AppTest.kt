package docket

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {

    private val app = docket()

    @Test
    fun `health endpoint returns OK`() {
        val response = app(Request(GET, "/health"))
        assertEquals(OK, response.status)
        assertEquals("OK", response.bodyString())
    }
}

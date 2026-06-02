package docket

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AppTest {

    private val app = docket()

    @Test
    fun `health endpoint returns 200 OK`() {
        val response = app(Request(GET, "/health"))
        assertEquals(OK, response.status)
    }

    @Test
    fun `health endpoint returns JSON content type`() {
        val response = app(Request(GET, "/health"))
        val contentType = response.header("Content-Type")
        assertTrue(contentType != null && contentType.contains("application/json"),
            "Expected application/json content type but got: $contentType")
    }

    @Test
    fun `health endpoint returns JSON body with status OK`() {
        val response = app(Request(GET, "/health"))
        assertEquals("""{"status":"OK"}""", response.bodyString())
    }
}

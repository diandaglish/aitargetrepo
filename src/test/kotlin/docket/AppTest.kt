package docket

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AppTest {

    private val app = docket()

    @Test
    fun `health endpoint returns OK`() {
        val response = app(Request(GET, "/health"))
        assertEquals(OK, response.status)
        assertEquals("OK", response.bodyString())
    }

    @Test
    fun `test get greeting success`() {
        val response = app(Request(GET, "/greeting?name=Alice"))
        assertEquals(OK, response.status)
        assertEquals("Hello, Alice", response.bodyString())
        assertTrue(response.header("Content-Type")?.contains("text/plain") == true)
    }

    @Test
    fun `test get greeting missing name param`() {
        val response = app(Request(GET, "/greeting"))
        assertEquals(BAD_REQUEST, response.status)
        assertEquals("Name parameter cannot be empty", response.bodyString())
    }

    @Test
    fun `test get greeting empty name param`() {
        val response = app(Request(GET, "/greeting?name="))
        assertEquals(BAD_REQUEST, response.status)
        assertEquals("Name parameter cannot be empty", response.bodyString())
    }

    @Test
    fun `test get greeting whitespace name param`() {
        val response = app(Request(GET, "/greeting?name=%20%20%20"))
        assertEquals(BAD_REQUEST, response.status)
        assertEquals("Name parameter cannot be empty", response.bodyString())
    }
}

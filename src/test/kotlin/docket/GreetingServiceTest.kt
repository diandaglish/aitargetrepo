package docket

import kotlin.test.Test
import kotlin.test.assertEquals

class GreetingServiceTest {

    private val service = GreetingService()

    @Test
    fun `getStaticGreeting returns fixed greeting`() {
        val result = service.getStaticGreeting()
        assertEquals("Hello, World!", result)
    }
}

package docket

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GreetingServiceTest {

    @Test
    fun `test generate greeting with valid name`() {
        val result = generateGreeting("Alice")
        assertEquals("Hello, Alice", result)
    }

    @Test
    fun `test generate greeting with multiple words`() {
        val result = generateGreeting("John Doe")
        assertEquals("Hello, John Doe", result)
    }

    @Test
    fun `test generate greeting raises error for none name`() {
        assertFailsWith<ValueError> {
            generateGreeting(null)
        }
    }

    @Test
    fun `test generate greeting raises error for empty string name`() {
        assertFailsWith<ValueError> {
            generateGreeting("")
        }
    }

    @Test
    fun `test generate greeting raises error for whitespace name`() {
        assertFailsWith<ValueError> {
            generateGreeting("   ")
        }
    }
}

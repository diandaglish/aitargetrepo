package docket

fun generateGreeting(name: String?): String {
    if (name == null || name.trim().isEmpty()) {
        throw ValueError("Name parameter cannot be empty")
    }
    return "Hello, $name"
}

class ValueError(message: String) : Exception(message)

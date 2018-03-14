package com.heitorcolangelo.repository.data.remote.exception

open class DefaultException(message: String, private val code: Int) : Exception(message) {

    override fun toString(): String {
        return "code: $code"
    }
}

class ServerException internal constructor(message: String, code: Int) :
    DefaultException(message, code)

class NotFoundException internal constructor(message: String, code: Int) :
    DefaultException(message, code)

class BadRequestException internal constructor(message: String, code: Int) :
    DefaultException(message, code)

class TooManyRequestsException internal constructor(message: String, code: Int) :
    DefaultException(message, code)

class BodyFormatException internal constructor(message: String, code: Int) :
    DefaultException(message, code)

fun handleException(code: Int) = when (code) {
    500 -> ServerException("Server Exception", code)
    400 -> BadRequestException("Bad Request Exception", code)
    404 -> NotFoundException("Not Found Exception", code)
    422 -> BodyFormatException("Body Format Exception", code)
    429 -> TooManyRequestsException("Too Many Requests Exception", code)
    else -> DefaultException("Unmapped Http Exception", code)
}
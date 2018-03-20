package com.heitorcolangelo.repository

import com.heitorcolangelo.repository.data.remote.exception.BadRequestException
import com.heitorcolangelo.repository.data.remote.exception.BodyFormatException
import com.heitorcolangelo.repository.data.remote.exception.DefaultException
import com.heitorcolangelo.repository.data.remote.exception.NotFoundException
import com.heitorcolangelo.repository.data.remote.exception.ServerException
import com.heitorcolangelo.repository.data.remote.exception.TooManyRequestsException
import com.heitorcolangelo.repository.data.remote.exception.handleException
import org.junit.Assert.assertTrue
import org.junit.Test

class ErrorHandlerTest {

    @Test
    fun withErrorCode_500_shouldThrow_serverException() {
        val current = handleException(500)
        assertTrue(
            "Expected ${ServerException::class.java.simpleName} " +
                "but was ${current::class.java.simpleName}",
            current is ServerException)
    }

    @Test
    fun withErrorCode_400_shouldThrow_BadRequestException() {
        val current = handleException(400)
        assertTrue(
            "Expected ${BadRequestException::class.java.simpleName} " +
                "but was ${current::class.java.simpleName}",
            current is BadRequestException)
    }

    @Test
    fun withErrorCode_404_shouldThrow_NotFoundException() {
        val current = handleException(404)
        assertTrue(
            "Expected ${NotFoundException::class.java.simpleName} " +
                "but was ${current::class.java.simpleName}",
            current is NotFoundException)
    }

    @Test
    fun withErrorCode_422_shouldThrow_BodyFormatException() {
        val current = handleException(422)
        assertTrue(
            "Expected ${BodyFormatException::class.java.simpleName} " +
                "but was ${current::class.java.simpleName}",
            current is BodyFormatException)
    }

    @Test
    fun withErrorCode_429_shouldThrow_TooManyRequestsException() {
        val current = handleException(429)
        assertTrue(
            "Expected ${TooManyRequestsException::class.java.simpleName} " +
                "but was ${current::class.java.simpleName}",
            current is TooManyRequestsException)
    }

    @Test
    fun withErrorCode_notMapped_shouldThrow_DefaultException() {
        val current = handleException(429)
        assertTrue(
            "Expected ${DefaultException::class.java.simpleName} " +
                "but was ${current::class.java.simpleName}",
            current is DefaultException)
    }
}
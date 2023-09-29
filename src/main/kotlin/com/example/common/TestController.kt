package com.example.common

import com.example.event.Publisher
import com.example.util.logger
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject

@Controller("/test")
class TestController {
    private val log = logger()

    @Inject
    lateinit var publisher: Publisher
    @Get("/simple/{params}")
    suspend fun pathVariable(params: Long): Long? {
        publisher.emitEventNumber(params)
        log.error("This is a test error")
        return params
    }

    @Get("/explicit/{params}")
    suspend fun pathVariableExplicit(@PathVariable params: String?): String? {
        return params
    }

    @Get("/query-params")
    suspend fun queryParams(@QueryValue first: String?, @QueryValue second: String?): String? {
        return "$first $second"
    }

    @Get("/complex-query-params{?request*}")
    suspend fun complexQueryParams(request: ComplexInput): String? {
        return "${request.first} ${request.second}"
    }

    @Post("/post-body")
    suspend fun postBody(@Body request: ComplexInput): String? {
        return "${request.first} ${request.second}"
    }

    @Get("/test-sentry")
    suspend fun testSentry() {
        throw ArithmeticException("Capture in Entry")
    }
}

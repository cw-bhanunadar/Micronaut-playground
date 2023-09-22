package com.example.common

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue

@Controller("/test")
class TestController {

    @Get("/simple/{params}")
    suspend fun pathVariable(params: String?): String? {
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
}

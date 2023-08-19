package com.example.exceptions

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.http.HttpStatus

data class ErrorResponse(
    @JsonProperty("errorCode")
    var errorCode: String = "",

    @JsonProperty("message")
    var errorMessage: String? = "",

    @JsonProperty("httpStatus")
    var httpStatus: HttpStatus?,
)

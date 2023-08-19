package com.example.exceptions

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Error
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton

@Singleton
class CustomExceptionHandler : ExceptionHandler<Exception, HttpResponse<ErrorResponse>> {

    @Error(global = true, exception = Exception::class)
    override fun handle(request: HttpRequest<*>?, exception: Exception?): HttpResponse<ErrorResponse> {
        // We can log request and exception here
        var errorMessage: ErrorResponse
        when (exception) {
            is CustomException -> {
                errorMessage =
                    ErrorResponse(
                        exception.error.code,
                        exception.error.getMessage(exception.context),
                        exception.error.httpStatus
                    )
            } else -> {
                errorMessage = ErrorResponse(
                    MasterErrorCodes.ERR_1000.code,
                    exception?.message,
                    HttpStatus.INTERNAL_SERVER_ERROR
                )
            }
        }
        return getResponse(errorMessage.httpStatus, errorMessage)
    }

    private fun getResponse(httpStatus: HttpStatus?, errorMessage: ErrorResponse): HttpResponse<ErrorResponse> {
        return if (httpStatus?.equals(HttpStatus.BAD_REQUEST) == true)
            HttpResponse.badRequest(errorMessage)
        else if (httpStatus?.equals(HttpStatus.SERVICE_UNAVAILABLE) == true)
            HttpResponse.serverError(errorMessage)
        else if (httpStatus?.equals(HttpStatus.INTERNAL_SERVER_ERROR) == true)
            HttpResponse.serverError(errorMessage)
        else if (httpStatus?.equals(HttpStatus.NOT_FOUND) == true)
            HttpResponse.notFound(errorMessage)
        else if (httpStatus?.equals(HttpStatus.UNAUTHORIZED) == true)
            HttpResponse.unauthorized()
        else
            HttpResponse.serverError(errorMessage)
    }
}

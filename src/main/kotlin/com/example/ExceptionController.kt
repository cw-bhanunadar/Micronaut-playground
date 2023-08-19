package com.example

import com.example.exceptions.CustomException
import com.example.exceptions.MasterErrorCodes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/exception")
class ExceptionController {

    @Get("/")
    suspend fun exception(): Long {
        val ans = 1 / 0
        return 1
    }

    @Get("/custom")
    suspend fun customException(): Long {
        throw CustomException(MasterErrorCodes.ERR_1000, "Custom Error some business logic failed")
        return 1
    }
}

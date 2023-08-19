package com.example.exceptions

import io.micronaut.http.HttpStatus

enum class MasterErrorCodes(
    var code: String,
    var message: String,
    var httpStatus: HttpStatus
) {
    ERR_1000("ERR_1000", "BAD Request", HttpStatus.BAD_REQUEST);

    fun getMessage(param: String): String {
        if (param != null) {
            return "$message $param"
        }
        return message
    }
}

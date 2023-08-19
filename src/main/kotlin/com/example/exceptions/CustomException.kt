package com.example.exceptions

class CustomException(var error: MasterErrorCodes, var context: String) : RuntimeException()

package com.example.common

import io.micronaut.core.annotation.Introspected

@Introspected
data class ComplexInput(
    var first: String?,
    var second: String?,
    var profession: String?
)


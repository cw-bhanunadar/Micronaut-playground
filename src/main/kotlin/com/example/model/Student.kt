package com.example.model

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity(value = "students")
@Introspected
data class Student(
    @field:Id @GeneratedValue var id: Long?,
    var name: String?
)

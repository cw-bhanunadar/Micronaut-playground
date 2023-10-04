package com.example.controller

import com.example.model.Student
import com.example.repository.StudentRepository
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject

@Controller("/student")
class StudentController {

    @Inject
    lateinit var studentRepository: StudentRepository
    @Post("/")
    suspend fun save(@Body student: Student): Student {
        return studentRepository.save(student)
    }

    @Get("/{id}")
    suspend fun get(@PathVariable id: Long): Student? {
        return studentRepository.findById(id)
    }
}

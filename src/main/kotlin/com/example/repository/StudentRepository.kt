package com.example.repository

import com.example.model.Student
import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.MYSQL)
interface StudentRepository : CrudRepository<Student, Long> {
    @Query("Select * from students where name = :name")
    suspend fun getStudents(name: String): List<Student>
}

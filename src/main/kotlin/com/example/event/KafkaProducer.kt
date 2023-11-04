package com.example.event

import com.example.model.Student
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface KafkaProducer {
    @Topic("student")
    fun send(student: Student?)
}

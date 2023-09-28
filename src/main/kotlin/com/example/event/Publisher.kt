package com.example.event

import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitClient

@RabbitClient("rabbitmq-demo")
interface Publisher {
    @Queue("event-number")
    @Binding("event.number")
    suspend fun emitEventNumber(eventNumber: Long)
}

package com.example.event

import com.example.util.logger
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener

@RabbitListener
class Consumer {
    @Queue("event-number", prefetch = 1)
    fun consumeEventNumber(eventNumber: Long) {
        logger().error("Successfully received event" + eventNumber.toString())
        throw ArithmeticException()
    }
}

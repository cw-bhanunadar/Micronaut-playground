package com.example.exceptions

import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import io.micronaut.rabbitmq.exception.DefaultRabbitListenerExceptionHandler
import jakarta.inject.Singleton

@Factory
class RabbitMqListenerErrorHandler {
    @Replaces(DefaultRabbitListenerExceptionHandler::class)
    @Singleton
    fun handlerConfig(): RabbitMqCustomListenerErrorHandler {
        return RabbitMqCustomListenerErrorHandler()
    }
}
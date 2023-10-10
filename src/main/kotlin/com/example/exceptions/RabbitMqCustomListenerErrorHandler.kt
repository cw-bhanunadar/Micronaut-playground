package com.example.exceptions

import com.fasterxml.jackson.module.kotlin.jsonMapper
import io.micronaut.rabbitmq.exception.RabbitListenerException
import io.micronaut.rabbitmq.exception.RabbitListenerExceptionHandler
import io.sentry.Sentry
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RabbitMqCustomListenerErrorHandler : RabbitListenerExceptionHandler {
    val log: Logger = LoggerFactory.getLogger("RabbiTMq")
    override fun handle(exception: RabbitListenerException?) {
        log.error(exception.toString())
        val messageState = exception?.messageState
        if (messageState!!.isPresent) {
            val data = String(messageState.get().body, Charsets.UTF_8)
            Sentry.configureScope {
                it.setContexts("message", data)
                it.setContexts("binding_key", messageState.get().envelope.routingKey)
                it.setContexts("properties", jsonMapper().writeValueAsString(messageState.get().properties))
            }
            Sentry.captureException(exception)
        }
    }
}

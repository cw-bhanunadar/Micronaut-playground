package com.example.common

import com.rabbitmq.client.Channel
import io.micronaut.rabbitmq.connect.ChannelInitializer
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import jakarta.inject.Singleton
@Singleton
class RabbitMqBootstrap : ChannelInitializer() {

    @EventListener
    fun onStartupEvent(@Suppress("UNUSED_PARAMETER") event: ServerStartupEvent) {
        initialize(null, "")
    }

    override fun initialize(channel: Channel?, name: String) {
        channel?.exchangeDeclare("rabbitmq-demo", "topic", true)

        channel?.queueDeclare("event-number", true, false, false, null)
        channel?.queueBind("event-number", "rabbitmq-demo", "event.number", null)
    }
}

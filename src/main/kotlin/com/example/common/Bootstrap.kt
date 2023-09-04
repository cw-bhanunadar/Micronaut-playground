package com.example.common

import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class Bootstrap {

    val log: Logger = LoggerFactory.getLogger("Bootstrap")
    @EventListener
    fun onStartupEvent(event: ServerStartupEvent) {
        log.info("This Event got trigger during Startup")
    }
}

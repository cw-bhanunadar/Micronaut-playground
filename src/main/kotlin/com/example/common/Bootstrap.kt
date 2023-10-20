package com.example.common

import io.micronaut.context.annotation.Value
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import io.sentry.Sentry
import io.sentry.SentryOptions
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class Bootstrap {

    val log: Logger = LoggerFactory.getLogger("Bootstrap")

    @Inject
    lateinit var sentryDsn: SentryConfiguration

    @EventListener
    fun onStartupEvent(event: ServerStartupEvent) {
        configureSentry()
        log.info("This Event got trigger during Startup")
        log.error("This Dummy Event got trigger during Startup")
        log.error("This Event got trigger during Startup")
    }
    private fun configureSentry() {

        Sentry.init { options: SentryOptions ->
            options.dsn = "https://50974ac4ec3a89a84b4b70e3c5e02f6b@o4505965347536896.ingest.sentry.io/4505965348978688"
            // Set tracesSampleRate to 1.0 to capture 100% of transactions for performance monitoring.
            // We recommend adjusting this value in production.
            options.tracesSampleRate = 1.0
            // When first trying Sentry it's good to see what the SDK is doing:
            // options.setDebug(true)
        }
    }
}

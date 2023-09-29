package com.example.common

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("sentry")
class SentryConfiguration {
    var dsn: String? = null
}

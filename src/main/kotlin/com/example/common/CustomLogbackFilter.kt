package com.example.common

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.spi.FilterReply

class CustomLogbackFilter : ch.qos.logback.core.filter.Filter<ILoggingEvent?>() {

    override fun decide(event: ILoggingEvent?): FilterReply? {
        return if (event?.getMessage()?.contains("Dummy") == true) {
            FilterReply.DENY
        } else {
            FilterReply.ACCEPT
        }
    }
}

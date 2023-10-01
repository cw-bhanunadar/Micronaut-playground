package com.example.event

import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton

@Singleton
class CronJob {

    @Scheduled(cron = "* * * * *") // Specify your cron expression here
    fun executeScheduledTask() {
        // Your task logic goes here
        println("Cron job executed at every minute")
    }
}

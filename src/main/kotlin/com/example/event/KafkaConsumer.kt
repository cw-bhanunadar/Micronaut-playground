package com.example.event

import com.example.model.Student
import com.example.util.logger
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
class KafkaConsumer {
    @Topic("student")
    fun receive(
        student: Student?
    ) {
        logger().error("Successfully received student" + student)
    }
}

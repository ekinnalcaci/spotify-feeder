@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.bigdataproject.spotifyfeeder.infrastructure.api

import com.bigdataproject.spotifyfeeder.infrastructure.kafka.KafkaProducer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// Used to manage kafka message handling

@RestController
@RequestMapping("/kafka-management")
class KafkaController(
    private val kafkaProducer: KafkaProducer,
) {
    @PostMapping("/produce-message")
    fun produceMessage(
        @RequestBody message: Any,
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(kafkaProducer.produce(message))
    }
}

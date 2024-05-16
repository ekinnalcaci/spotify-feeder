package com.bigdataproject.spotifyfeeder.infrastructure.kafka

import com.bigdataproject.spotifyfeeder.infrastructure.kafka.KafkaConfig.Companion.SONG_CHANGE_TOPIC_NAME
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, String>) {
    fun produce(message: String) {
        kafkaTemplate.send(SONG_CHANGE_TOPIC_NAME, message)
    }
}

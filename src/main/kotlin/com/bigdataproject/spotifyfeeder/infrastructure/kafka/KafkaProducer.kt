package com.bigdataproject.spotifyfeeder.infrastructure.kafka

import com.bigdataproject.spotifyfeeder.infrastructure.kafka.KafkaConfig.Companion.SONG_CHANGE_TOPIC_NAME
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper,
) {
    fun produce(message: Any) {
        kafkaTemplate.send(SONG_CHANGE_TOPIC_NAME, objectMapper.writeValueAsString(message))
    }
}

package com.bigdataproject.spotifyfeeder.infrastructure.kafka

import com.bigdataproject.spotifyfeeder.infrastructure.kafka.KafkaConfig.Companion.GROUP_ID
import com.bigdataproject.spotifyfeeder.infrastructure.kafka.KafkaConfig.Companion.SONG_CHANGE_TOPIC_NAME
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer {
    private val logger = LoggerFactory.getLogger(KafkaConsumer::class.java)

    @KafkaListener(topics = [SONG_CHANGE_TOPIC_NAME], groupId = GROUP_ID)
    fun consume(message: String) {
        logger.info("Consumed message: $message")
    }
}

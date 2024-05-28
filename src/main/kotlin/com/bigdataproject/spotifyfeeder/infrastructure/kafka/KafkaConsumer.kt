package com.bigdataproject.spotifyfeeder.infrastructure.kafka

import com.bigdataproject.spotifyfeeder.application.SongUpdaterService
import com.bigdataproject.spotifyfeeder.infrastructure.cron.UpdatePopularity
import com.bigdataproject.spotifyfeeder.infrastructure.kafka.KafkaConfig.Companion.GROUP_ID
import com.bigdataproject.spotifyfeeder.infrastructure.kafka.KafkaConfig.Companion.SONG_CHANGE_TOPIC_NAME
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class KafkaConsumer(
    val songUpdaterService: SongUpdaterService,
    val objectMapper: ObjectMapper,
) {
    private val logger = LoggerFactory.getLogger(KafkaConsumer::class.java)

    @KafkaListener(topics = [SONG_CHANGE_TOPIC_NAME], groupId = GROUP_ID)
    fun consume(message: String) {
        logger.info("Consumed message: $message")
        val updatePopularityMessage = objectMapper.readValue(message, UpdatePopularity::class.java)
        if (Random.nextBoolean()) {
            songUpdaterService.increasePopularity(updatePopularityMessage.songId, updatePopularityMessage.amount)
        } else {
            songUpdaterService.decreaseArtistPopularity(updatePopularityMessage.songId, updatePopularityMessage.amount)
        }
    }
}

package com.bigdataproject.spotifyfeeder.infrastructure.cron

import com.bigdataproject.spotifyfeeder.application.persistance.SongRepository
import com.bigdataproject.spotifyfeeder.infrastructure.kafka.KafkaProducer
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
@EnableScheduling
class PopularityUpdaterJob(
    val kafkaProducer: KafkaProducer,
    val songRepository: SongRepository,
) {
    @Scheduled(fixedRate = 2000)
    fun updateArtistPopularity() {
        val songId = songRepository.getRandomSongId()
        val amount = Random.nextInt(from = 0, until = 15)
        kafkaProducer.produce(UpdatePopularity(songId, amount))
    }
}

data class UpdatePopularity(
    val songId: String,
    val amount: Int,
)

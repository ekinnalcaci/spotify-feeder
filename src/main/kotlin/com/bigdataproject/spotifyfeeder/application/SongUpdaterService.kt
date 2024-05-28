package com.bigdataproject.spotifyfeeder.application

import com.bigdataproject.spotifyfeeder.application.persistance.SongRepository
import org.springframework.stereotype.Service

@Service
class SongUpdaterService(
    val songRepository: SongRepository,
) {
    fun increasePopularity(
        songId: String,
        amount: Int,
    ) {
        val song = songRepository.getSongById(songId) ?: return
        song.apply { popularity += amount }
        songRepository.updateArtistFollowers(song)
    }

    fun decreaseArtistPopularity(
        songId: String,
        amount: Int,
    ) {
        val song = songRepository.getSongById(songId) ?: return
        if (song.popularity - amount < 0) {
            throw IllegalStateException("Song with id $songId cannot have negative popularity")
        }
        song.apply { popularity -= amount }
        songRepository.updateArtistFollowers(song)
    }
}

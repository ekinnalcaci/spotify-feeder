package com.bigdataproject.spotifyfeeder.application

import com.bigdataproject.spotifyfeeder.application.persistance.SongRepository
import org.springframework.stereotype.Service

@Service
class SongUpdaterService(
    val songRepository: SongRepository,
) {
    fun increaseArtistFollowers(
        songId: String,
        amount: Int,
    ) {
        val song = songRepository.getSongById(songId) ?: return
        song.apply { artistFollowers += amount }
        songRepository.updateArtistFollowers(song)
    }

    fun decreaseArtistFollowers(
        songId: String,
        amount: Int,
    ) {
        val song = songRepository.getSongById(songId) ?: return
        if (song.artistFollowers - amount < 0) {
            throw IllegalStateException("Song with id $songId cannot have negative followers")
        }
        song.apply { artistFollowers -= amount }
        songRepository.updateArtistFollowers(song)
    }
}

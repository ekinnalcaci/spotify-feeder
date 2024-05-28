package com.bigdataproject.spotifyfeeder.application.persistance

import com.bigdataproject.spotifyfeeder.domain.Song

interface SongRepository {
    fun getSongByName(songName: String): List<Song>

    fun addSong(song: Song)

    fun getSongById(id: String): Song?

    fun updateArtistFollowers(song: Song)

    fun getRandomSongId(): String
}

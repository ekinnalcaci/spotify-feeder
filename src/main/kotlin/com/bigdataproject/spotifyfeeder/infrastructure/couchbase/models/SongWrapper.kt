package com.bigdataproject.spotifyfeeder.infrastructure.couchbase.models

import com.bigdataproject.spotifyfeeder.domain.Song
import com.fasterxml.jackson.annotation.JsonProperty

data class SongWrapper(
    @JsonProperty("Song") val song: Song,
) {
    companion object {
        fun SongWrapper.toSong(): Song {
            return Song(
                id = this.song.id,
                name = this.song.name,
                artist = this.song.artist,
                popularity = this.song.popularity,
                albumId = this.song.albumId,
                albumName = this.song.albumName,
                releaseDate = this.song.releaseDate,
                playlistName = this.song.playlistName,
                playlistId = this.song.playlistId,
                playlistGenre = this.song.playlistGenre,
                playlistSubGenre = this.song.playlistSubGenre,
                danceability = this.song.danceability,
                energy = this.song.energy,
                key = this.song.key,
                loudness = this.song.loudness,
                mode = this.song.mode,
                speechiness = this.song.speechiness,
                acousticness = this.song.acousticness,
                instrumentalness = this.song.instrumentalness,
                liveness = this.song.liveness,
                valence = this.song.valence,
                tempo = this.song.tempo,
                durationMilis = this.song.durationMilis,
            )
        }

        fun List<SongWrapper>.toSongList(): List<Song> {
            return this.map { it.toSong() }
        }
    }
}

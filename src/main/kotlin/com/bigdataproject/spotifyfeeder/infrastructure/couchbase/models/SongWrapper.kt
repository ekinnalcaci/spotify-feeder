package com.bigdataproject.spotifyfeeder.infrastructure.couchbase.models

import com.bigdataproject.spotifyfeeder.domain.Song
import com.fasterxml.jackson.annotation.JsonProperty

data class SongWrapper(
    @JsonProperty("Song") val song: Song,
) {
    companion object {
        fun SongWrapper.toSong(): Song {
            return Song(this.song.id, this.song.name, this.song.artist, this.song.durationSeconds)
        }

        fun List<SongWrapper>.toSongList(): List<Song> {
            return this.map { Song(it.song.id, it.song.name, it.song.artist, it.song.durationSeconds) }
        }
    }
}

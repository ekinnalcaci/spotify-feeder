@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.bigdataproject.spotifyfeeder.infrastructure.api

import com.bigdataproject.spotifyfeeder.application.persistance.SongRepository
import com.bigdataproject.spotifyfeeder.domain.Song
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// Used to manage couchbase objects

@RestController
@RequestMapping("/couchbase-management")
class CouchbaseController(
    private val songRepository: SongRepository,
) {
    @PutMapping("/add-song")
    fun addSong(
        @RequestBody song: Song,
    ): ResponseEntity<Unit> {
        songRepository.addSong(song)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/search-song-name")
    fun searchSongByName(
        @RequestParam(required = true) songName: String,
    ): ResponseEntity<List<Song>> {
        return ResponseEntity.ok(songRepository.getSongByName(songName))
    }

    @GetMapping("/{id}")
    fun searchSongById(
        @PathVariable id: String,
    ): ResponseEntity<Song> {
        return ResponseEntity.ok(songRepository.getSongById(id))
    }
}

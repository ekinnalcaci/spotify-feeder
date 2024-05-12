@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.bigdataproject.spotifyfeeder.infrastructure.api

import com.bigdataproject.spotifyfeeder.application.ElasticSearchService
import com.bigdataproject.spotifyfeeder.domain.Song
import org.apache.http.StatusLine
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// Used to manage elastic indexes and send elastic queries

@RestController
@RequestMapping("/elastic-management")
class ElasticSearchController(
    private val elasticSearchService: ElasticSearchService,
) {
    @PutMapping("/create-songs-index")
    fun createIndex(): ResponseEntity<StatusLine> {
        val response = elasticSearchService.createIndex()
        val responseCode = HttpStatusCode.valueOf(response.statusCode)
        return ResponseEntity.status(responseCode).body(response)
    }

    @PutMapping("/add-song")
    fun addSong(
        @RequestBody song: Song,
    ): ResponseEntity<StatusLine> {
        val response = elasticSearchService.addSong(song)
        val responseCode = HttpStatusCode.valueOf(response.statusCode)
        return ResponseEntity.status(responseCode).body(response)
    }

    @GetMapping("/search-song-name")
    fun searchSongByName(
        @RequestParam(required = true) songName: String,
    ): ResponseEntity<List<Song>> {
        val response = elasticSearchService.searchSongByName(songName)
        return ResponseEntity.ok(response)
    }
}

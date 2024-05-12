@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.bigdataproject.spotifyfeeder.infrastructure.api

import com.bigdataproject.spotifyfeeder.application.ElasticSearchService
import com.bigdataproject.spotifyfeeder.domain.Song
import org.springframework.web.bind.annotation.*

// Used to manage elastic indexes and send elastic queries

@RestController
@RequestMapping("/elastic-management")
class ElasticSearchController(
    private val elasticSearchService: ElasticSearchService,
) {
    @PutMapping("create-index")
    fun createIndex(): Boolean {
        return elasticSearchService.createIndex()
    }

    @PutMapping("add-song")
    fun addSong(
        @RequestBody song: Song,
    ): Boolean {
        return elasticSearchService.addSong(song)
    }

    @PutMapping("search-song-name")
    fun searchSongName(
        @RequestParam(required = true) songName: String,
    ): Boolean {
        return elasticSearchService.searchSongName(songName)
    }
}

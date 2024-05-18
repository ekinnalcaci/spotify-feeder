@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.bigdataproject.spotifyfeeder.infrastructure.api

import com.bigdataproject.spotifyfeeder.application.SongUpdaterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// Used to update song information

@RestController
@RequestMapping("/couchbase-management")
class SongUpdaterController(
    private val songUpdaterService: SongUpdaterService,
) {
    @PostMapping("/increment-followers")
    fun incrementFollowers(
        @RequestParam(required = true) id: String,
        @RequestParam(required = true) amount: Int,
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(songUpdaterService.increaseArtistFollowers(id, amount))
    }

    @PostMapping("/decrement-followers")
    fun decrementFollowers(
        @RequestParam(required = true) id: String,
        @RequestParam(required = true) amount: Int,
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(songUpdaterService.decreaseArtistFollowers(id, amount))
    }
}

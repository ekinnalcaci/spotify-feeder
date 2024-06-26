@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.bigdataproject.spotifyfeeder.infrastructure.api

import com.bigdataproject.spotifyfeeder.application.SongUpdaterService
import com.bigdataproject.spotifyfeeder.infrastructure.cron.PopularityUpdaterJob
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// Used to update song information

@RestController
@RequestMapping("/couchbase-management")
class SongUpdaterController(
    private val songUpdaterService: SongUpdaterService,
    private val popularityUpdaterJob: PopularityUpdaterJob,
) {
    @PostMapping("/increment-popularity")
    fun incrementPopularity(
        @RequestParam(required = true) id: String,
        @RequestParam(required = true) amount: Int,
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(songUpdaterService.increasePopularity(id, amount))
    }

    @PostMapping("/decrement-popularity")
    fun decrementPopularity(
        @RequestParam(required = true) id: String,
        @RequestParam(required = true) amount: Int,
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(songUpdaterService.decreaseArtistPopularity(id, amount))
    }

    @PostMapping("/update-popularity-randomly")
    fun updatePopularityRandomly(): ResponseEntity<Unit> {
        return ResponseEntity.ok(popularityUpdaterJob.updateArtistPopularity())
    }
}

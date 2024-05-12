package com.bigdataproject.spotifyfeeder.infrastructure.couchbase.models

import com.bigdataproject.spotifyfeeder.domain.Song
import com.fasterxml.jackson.annotation.JsonProperty

data class SongWrapper(
    @JsonProperty("Song") val song: Song,
)

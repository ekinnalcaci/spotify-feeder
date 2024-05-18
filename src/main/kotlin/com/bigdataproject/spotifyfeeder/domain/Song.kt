package com.bigdataproject.spotifyfeeder.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "spotify-songs")
data class Song(
    @JsonProperty("id") @Id val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("artist") val artist: String,
    @JsonProperty("durationSeconds") val durationSeconds: Double,
    @JsonProperty("artistFollowers") var artistFollowers: Long = 0,
)

package com.bigdataproject.spotifyfeeder.domain

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "spotify-songs")
data class Song(
    @Id val id: String,
    val name: String,
    val artist: String,
    val durationSeconds: Long,
)

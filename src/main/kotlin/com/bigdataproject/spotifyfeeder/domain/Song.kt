package com.bigdataproject.spotifyfeeder.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "spotify-songs")
data class Song(
    @JsonProperty("track_id") @Id val id: String,
    @JsonProperty("track_name") val name: String,
    @JsonProperty("track_artist") val artist: String,
    @JsonProperty("track_popularity") var popularity: Long,
    @JsonProperty("track_album_id") val albumId: String,
    @JsonProperty("track_album_name") val albumName: String,
    @JsonProperty("track_album_release_date") val releaseDate: String,
    @JsonProperty("playlist_name") val playlistName: String,
    @JsonProperty("playlist_id") val playlistId: String,
    @JsonProperty("playlist_genre") val playlistGenre: String,
    @JsonProperty("playlist_subgenre") val playlistSubGenre: String,
    @JsonProperty("danceability") val danceability: Double,
    @JsonProperty("energy") val energy: Double,
    @JsonProperty("key") val key: String,
    @JsonProperty("loudness") val loudness: Double,
    @JsonProperty("mode") val mode: Int,
    @JsonProperty("speechiness") val speechiness: Double,
    @JsonProperty("acousticness") val acousticness: Double,
    @JsonProperty("instrumentalness") val instrumentalness: Double,
    @JsonProperty("liveness") val liveness: Double,
    @JsonProperty("valence") val valence: Double,
    @JsonProperty("tempo") val tempo: Double,
    @JsonProperty("duration_ms") val durationMilis: Long,
)

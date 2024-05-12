package com.bigdataproject.spotifyfeeder.application

import com.bigdataproject.spotifyfeeder.application.extensions.toSongs
import com.bigdataproject.spotifyfeeder.domain.Song
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.StatusLine
import org.elasticsearch.client.Request
import org.elasticsearch.client.RestClient
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Service

@EnableAutoConfiguration
@Service
class ElasticSearchService(
    private val elasticClient: RestClient,
    private val objectMapper: ObjectMapper,
) {
    companion object {
        const val SONGS_INDEX_NAME = "spotify-songs"
        const val DOC_ENDPOINT = "/$SONGS_INDEX_NAME/_doc"
        const val SEARCH_ENDPOINT = "/$SONGS_INDEX_NAME/_search"
    }

    fun createIndex(): StatusLine {
        val request = Request("PUT", "/$SONGS_INDEX_NAME")
        val response = elasticClient.performRequest(request)
        return response.statusLine
    }

    fun addSong(song: Song): StatusLine {
        val request = Request("POST", DOC_ENDPOINT)
        val body = objectMapper.writeValueAsString(song)
        request.setJsonEntity(body)
        return elasticClient.performRequest(request).statusLine
    }

    fun searchSongByName(songName: String): List<Song> {
        val request = Request("GET", SEARCH_ENDPOINT)
        val body =
            """
            {
                "query": {
                    "match": {
                        "name": "$songName"
                    }
                }
            }
            """.trimIndent()
        request.setJsonEntity(body)
        val response = elasticClient.performRequest(request)
        return response.toSongs(objectMapper)
    }
}

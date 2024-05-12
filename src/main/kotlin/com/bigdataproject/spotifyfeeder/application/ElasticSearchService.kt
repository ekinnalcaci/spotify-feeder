package com.bigdataproject.spotifyfeeder.application

import com.bigdataproject.spotifyfeeder.domain.Song
import com.fasterxml.jackson.databind.ObjectMapper
import org.elasticsearch.client.Request
import org.elasticsearch.client.RestClient
import org.springframework.stereotype.Service

@Service
class ElasticSearchService(
    private val elasticClient: RestClient,
    private val objectMapper: ObjectMapper,
) {
    companion object {
        const val SONGS_INDEX_NAME = "spotify-songs"
        const val DOC_ENDPOINT = "/_doc"
        const val SEARCH_ENDPOINT = "/_search"
    }

    fun createIndex(): Boolean {
        val request = Request("PUT", "/$SONGS_INDEX_NAME")
        val response = elasticClient.performRequest(request)
        return response.statusLine.statusCode == 200
    }

    fun addSong(song: Song): Boolean {
        val request = Request("POST", "/$SONGS_INDEX_NAME/_doc")
        val body = objectMapper.writeValueAsString(song)
        request.setJsonEntity(body)
        val response = elasticClient.performRequest(request)
        return response.statusLine.statusCode == 200
    }

    fun searchSongName(songName: String): Boolean {
        val request = Request("GET", "/$SONGS_INDEX_NAME/_doc/_search")
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
        return response.statusLine.statusCode == 200
    }
}

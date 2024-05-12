package com.bigdataproject.spotifyfeeder.infrastructure.extensions

import com.bigdataproject.spotifyfeeder.domain.Song
import com.fasterxml.jackson.databind.ObjectMapper
import org.elasticsearch.client.Response

fun Response.toSong(objectMapper: ObjectMapper): Song? {
    val content = this.entity.content
    val string = content.bufferedReader().use { it.readText() }
    val node = objectMapper.readTree(string)
    val hitsNode = node["hits"]["hits"]
    if (hitsNode.isArray && hitsNode.size() > 0) {
        val sourceNode = hitsNode[0]["_source"]
        return objectMapper.treeToValue(sourceNode, Song::class.java)
    }
    return null
}

fun Response.toSongs(objectMapper: ObjectMapper): List<Song> {
    val content = this.entity.content
    val string = content.bufferedReader().use { it.readText() }
    val node = objectMapper.readTree(string)
    val hitsNode = node["hits"]["hits"]

    if (hitsNode.isArray) {
        return hitsNode.map {
            val sourceNode = it["_source"]
            objectMapper.treeToValue(sourceNode, Song::class.java)
        }
    }
    return emptyList()
}

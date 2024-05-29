package com.bigdataproject.spotifyfeeder.application.extensions

import com.bigdataproject.spotifyfeeder.domain.Song
import com.couchbase.client.java.json.JsonObject
import com.couchbase.client.java.query.QueryOptions
import com.fasterxml.jackson.databind.ObjectMapper
import org.elasticsearch.client.Response

fun Response.toSongs(objectMapper: ObjectMapper): List<Song> {
    val content = this.entity.content
    val response = content.bufferedReader().use { it.readText() }
    val hitNodes = objectMapper.readTree(response)["hits"]["hits"]

    if (hitNodes.isArray) {
        return hitNodes.map {
            val sourceNode = it["_source"]["doc"]
            objectMapper.treeToValue(sourceNode, Song::class.java)
        }
    }
    return emptyList()
}

fun QueryOptions.withPairs(list: List<Pair<String, Any>>): QueryOptions {
    val jsonObject = JsonObject.create()
    list.forEach {
        jsonObject.put(it.first, it.second)
    }
    return this.parameters(jsonObject)
}

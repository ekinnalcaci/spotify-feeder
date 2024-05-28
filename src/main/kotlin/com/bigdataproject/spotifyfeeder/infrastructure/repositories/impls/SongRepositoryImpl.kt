package com.bigdataproject.spotifyfeeder.infrastructure.repositories.impls

import com.bigdataproject.spotifyfeeder.application.persistance.SongRepository
import com.bigdataproject.spotifyfeeder.domain.Song
import com.bigdataproject.spotifyfeeder.infrastructure.couchbase.models.SongWrapper
import com.bigdataproject.spotifyfeeder.infrastructure.couchbase.models.SongWrapper.Companion.toSongList
import com.couchbase.client.java.Cluster
import com.couchbase.client.java.Collection
import org.springframework.stereotype.Repository
import kotlin.random.Random

@Repository
class SongRepositoryImpl(
    private val cluster: Cluster,
    private val collection: Collection,
) : SongRepository {
    companion object {
        const val DOC_COUNT = 28356
        // TO DO bunu cache'leyebileceğin cb ye count isteği atan var a çevir
    }

    override fun getSongByName(songName: String): List<Song> {
        val query = "SELECT * FROM ${collection.bucketName()} WHERE track_name='$songName'"
        return cluster.query(query)
            .rowsAs(SongWrapper::class.java)
            .toSongList()
    }

    override fun addSong(song: Song) {
        collection.insert(song.id, song)
    }

    override fun getSongById(id: String): Song? {
        return collection.get(id)?.contentAs(Song::class.java)
    }

    override fun updateArtistFollowers(song: Song) {
        collection.upsert(song.id, song)
    }

    override fun getRandomSongId(): String {
        val query =
            "SELECT track_id FROM ${collection.bucketName()} LIMIT 1 OFFSET ${Random.nextInt(0, DOC_COUNT - 1)}"
        return cluster.query(query).rowsAsObject().first()["track_id"].toString()
    }
}

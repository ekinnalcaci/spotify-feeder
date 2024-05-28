package com.bigdataproject.spotifyfeeder.infrastructure.repositories.impls

import com.bigdataproject.spotifyfeeder.application.persistance.SongRepository
import com.bigdataproject.spotifyfeeder.domain.Song
import com.bigdataproject.spotifyfeeder.infrastructure.couchbase.models.SongWrapper
import com.bigdataproject.spotifyfeeder.infrastructure.couchbase.models.SongWrapper.Companion.toSongList
import com.couchbase.client.java.Cluster
import com.couchbase.client.java.Collection
import org.springframework.stereotype.Repository

@Repository
class SongRepositoryImpl(
    private val cluster: Cluster,
    private val collection: Collection,
) : SongRepository {
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
}

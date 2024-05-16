package com.bigdataproject.spotifyfeeder.infrastructure.couchbase

import com.couchbase.client.java.Bucket
import com.couchbase.client.java.Cluster
import com.couchbase.client.java.Collection
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
@EnableConfigurationProperties(CouchbaseProperties::class)
class CouchbaseConfig(
    private val properties: CouchbaseProperties,
) {
    private val logger = LoggerFactory.getLogger(CouchbaseConfig::class.java)

    @Bean(destroyMethod = "disconnect")
    fun couchbaseCluster(): Cluster {
        try {
            val cluster = Cluster.connect(properties.connectionString, properties.username, properties.password)
            cluster.waitUntilReady(Duration.ofSeconds(15))
            logger.info("Connected to couchbase cluster at ${properties.connectionString}.")
            return cluster
        } catch (ex: Exception) {
            throw ex
        }
    }

    @Bean
    fun couchbaseBucket(cluster: Cluster): Bucket {
        return cluster.bucket(properties.bucketName)
    }

    @Bean
    fun couchbaseCollection(bucket: Bucket): Collection? {
        return bucket.defaultCollection()
    }
}

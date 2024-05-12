package com.bigdataproject.spotifyfeeder.infrastructure.couchbase

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("couchbase")
class CouchbaseProperties {
    var connectionString: String = ""
    var username: String = ""
    var password: String = ""
    var bucketName: String = ""
}

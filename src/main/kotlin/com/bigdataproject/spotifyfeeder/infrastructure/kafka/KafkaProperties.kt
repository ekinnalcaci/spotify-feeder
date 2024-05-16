package com.bigdataproject.spotifyfeeder.infrastructure.kafka

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.kafka")
class KafkaProperties {
    lateinit var bootstrapServers: String
    lateinit var consumer: ConsumerProperties
    lateinit var producer: ProducerProperties

    class ConsumerProperties {
        lateinit var groupId: String
        lateinit var keyDeserializer: String
        lateinit var valueDeserializer: String
    }

    class ProducerProperties {
        lateinit var keySerializer: String
        lateinit var valueSerializer: String
    }
}

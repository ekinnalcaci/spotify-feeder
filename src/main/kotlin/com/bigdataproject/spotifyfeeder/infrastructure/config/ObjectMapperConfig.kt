package com.bigdataproject.spotifyfeeder.infrastructure.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfig {
    private val logger = LoggerFactory.getLogger(ObjectMapperConfig::class.java)

    @Bean
    fun objectMapper(): ObjectMapper {
        logger.info("ObjectMapper bean created.")
        return ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .registerKotlinModule()
    }
}

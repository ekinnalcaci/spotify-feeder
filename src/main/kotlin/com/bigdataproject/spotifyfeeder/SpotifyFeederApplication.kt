package com.bigdataproject.spotifyfeeder

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@OpenAPIDefinition(
    info =
        Info(
            title = "SpotifyFeeder Swagger",
            description = "SpotifyFeeder's spring boot application",
            contact =
                Contact(
                    name = "Ekin Nalcaci and Eda Nur Altunok",
                ),
            version = "1.4",
        ),
)
@EnableKafka
@SpringBootApplication
class SpotifyFeederApplication

fun main(args: Array<String>) {
    runApplication<SpotifyFeederApplication>(*args)
}

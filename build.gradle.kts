import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

group = "com.bigdataproject"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation("co.elastic.clients:elasticsearch-java:8.13.4")
    implementation("org.elasticsearch.client:elasticsearch-rest-client:8.13.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.5") {
        exclude("com.fasterxml.jackson.core")
    }
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    implementation("org.springframework.boot:spring-boot-starter-data-couchbase:3.2.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

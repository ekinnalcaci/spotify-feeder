import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient
import co.elastic.clients.json.jackson.JacksonJsonpMapper
import co.elastic.clients.transport.rest_client.RestClientTransport
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ElasticClientConfig() {
    private val logger = LoggerFactory.getLogger(ElasticClientConfig::class.java)

    @Bean(destroyMethod = "close")
    fun restClient(): RestClient {
        logger.info("RestClient bean created.")
        return RestClient.builder(
            HttpHost("localhost", 9200, "http"),
        ).build()
    }

    @Bean
    fun asyncClient(restClient: RestClient): ElasticsearchAsyncClient { // TODO async client migrate et
        logger.info("ElasticsearchAsyncClient bean created.")
        val transport = RestClientTransport(restClient, JacksonJsonpMapper(ObjectMapper()))
        return ElasticsearchAsyncClient(transport)
    }
}

package com.omtm.reciperecommender

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.*
import reactor.core.publisher.Mono

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
                .info(
                        Info()
                                .title("밥심: 레시피 추천 서비스")
                                .description("""
                                ### recipe recommender service
                                - recommend recipes
                                - search recipes
                            """.trimIndent())
//                                .version("0.0.1-SNAPSHOT")
                                .license(License().name("ⓒ 2020 TRAIN-J All rights reserved."))
                )
    }
}

class WebClientConfig {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val elasticsearchUrl: String = System.getenv()["ES_URL"] ?: throw IllegalArgumentException("set environment variable, ES_URL")

    fun webClient(): WebClient = WebClient.builder()
            .baseUrl(elasticsearchUrl)
            .filter(logRequest())
            .filter(logResponse())
            .build()

    private fun logRequest() = { clientRequest: ClientRequest, next: ExchangeFunction ->
        logger.info("Request: {} {}", clientRequest.method(), clientRequest.url())

        clientRequest.headers()
                .forEach { name, values ->
                    values.forEach { value -> logger.info("{}={}", name, value) }
                }
        // (name, values) -> values.forEach(value -> log.info("{}={}", name, value))

        next.exchange(clientRequest)
    }

    private fun logResponse() = ExchangeFilterFunction.ofResponseProcessor { clientResponse: ClientResponse ->
        logger.info("Response: {}",
                clientResponse
                        .headers().asHttpHeaders()
                        .forEach { name, values ->
                            values.forEach { value -> logger.debug("{} : {}", name, value) }
                        }
        )

        Mono.just(clientResponse)
    }
}
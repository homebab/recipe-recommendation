package com.omtm.reciperecommender

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.*
import reactor.core.publisher.Mono

@Service
class Service {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private val webClient: WebClient = WebClient.builder()
            .baseUrl("http://0.0.0.0:9200")
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


//        logger.info("Request: {} {}", clientRequest.method(), clientRequest.url())
//
//        clientRequest.headers()
//                .forEach { name, values -> values.forEach { value -> logger.info("{}={}", name, value) } }
//        // (name, values) -> values.forEach(value -> log.info("{}={}", name, value))
//
//        next.exchange(clientRequest)


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


    fun recommendRecipes(ingredients: String, size: Int): Flow<Any> = webClient
            .post()
            .uri("/omtm/recipe/_search")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .bodyValue("""
                {
                  "query": {
                    "function_score": {
                      "query": {
                        "multi_match": {
                          "query": "$ingredients",
                          "fields": ["description","title^0.5"]
                        }
                      },
                      "random_score": {
                        "field": "_seq_no"
                      }
                    }
                  },
                  "size": $size
                }
            """.trimIndent())
            .retrieve()
            .bodyToFlux(Any::class.java)
            .asFlow()

    fun searchRecipes(keywords: String, size: Int): Flow<Any> = webClient
            .post()
            .uri("/omtm/recipe/_search")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .bodyValue("""
                {
                  "query": {
                    "multi_match": {
                      "query": "$keywords",
                      "fields": ["description","title^0.5"]
                    }
                  },
                  "size": $size
                }
            """.trimIndent())
            .retrieve()
            .bodyToFlux(Any::class.java)
            .asFlow()


}

// TODO: not implemented
class QueryBuilder {}
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

    private val webClient: WebClient = WebClientConfig().webClient()

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
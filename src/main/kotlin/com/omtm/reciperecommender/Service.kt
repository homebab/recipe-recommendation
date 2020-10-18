package com.omtm.reciperecommender

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient

@Service
class Service {

    private val webClient: WebClient = WebClient.create("http://0.0.0.0:9200")


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


//    fun logRequest(): ExchangeFilterFunction {
//        return { clientRequest, next ->
//            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
//            clientRequest.headers()
//                    .forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
//            return next.exchange(clientRequest)
//        }
//    }
//
//    fun logResponse(): ExchangeFilterFunction {
//        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//            log.info("Response: {}", clientResponse.headers().asHttpHeaders().get("property-header"));
//            return Mono.just(clientResponse);
//        });
//    }


}

// TODO: not implemented
class QueryBuilder {}
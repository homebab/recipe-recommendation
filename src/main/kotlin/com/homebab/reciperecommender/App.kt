package com.homebab.reciperecommender

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//@OpenAPIDefinition(
//    servers = [Server(url = "/")]
//)
@SpringBootApplication
class RecipeRecommenderApplication

fun main(args: Array<String>) {
    runApplication<RecipeRecommenderApplication>(*args)
}

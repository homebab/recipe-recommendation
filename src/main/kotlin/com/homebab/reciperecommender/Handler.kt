package com.homebab.reciperecommender

import kotlinx.coroutines.FlowPreview
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class Handler(@Autowired private val service: Service) {

    @FlowPreview
    suspend fun recommendRecipes(request: ServerRequest): ServerResponse =
            ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .json()
                    .bodyAndAwait(
                            service.recommendRecipes(
                                    // TODO: validator
                                    request.queryParam("ingredients").orElseThrow(),
                                    request.queryParam("from").orElse("0").toInt(),
                                    request.queryParam("size").orElse("12").toInt()
                            )
                    )


    suspend fun searchRecipes(request: ServerRequest): ServerResponse =
            ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .json()
                    .bodyAndAwait(
                            service.searchRecipes(
                                    // TODO: validator
                                    request.queryParam("keywords").orElseThrow(),
                                    request.queryParam("from").orElse("0").toInt(),
                                    request.queryParam("size").orElse("12").toInt()
                            )
                    )

    suspend fun notifyRecipe(request: ServerRequest): ServerResponse {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(RecipeTemp("김치찌개", "맛있게 끓인다"))
    }

}
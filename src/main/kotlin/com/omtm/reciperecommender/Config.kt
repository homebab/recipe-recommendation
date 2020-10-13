package com.omtm.reciperecommender

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
                .info(
                        Info()
                                .title("한끼두끼: 레시피 추천 서비스")
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
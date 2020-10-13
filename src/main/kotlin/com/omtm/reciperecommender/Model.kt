package com.omtm.reciperecommender

import java.time.LocalDateTime

data class Recipe(val name: String, val description: String)

data class ESResponse(val took: Number, val time_out: Boolean, val hits: Any)

data class RecipePersist(
        val kind: String,
        val external_id: String,
        val published_at: LocalDateTime,
        val publisher: String,
        val title: String,
        val description: String,
        val thumbnails: Any
)

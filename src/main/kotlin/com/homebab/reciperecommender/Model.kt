package com.homebab.reciperecommender

import java.time.LocalDateTime

data class RecipeTemp(val name: String, val description: String)

data class InsideHits(
    val _index: String,
    val _type: String,
    val _id: String,
    val _score: Number,
    val _source: Recipe
)

data class OutsideHits(
    val total: Any,
    val max_score: Number,
    val hits: List<InsideHits>
)

data class ESResponse(
    val took: Number,
    val timed_out: Boolean,
    val _shards: Any,
    val hits: OutsideHits
)

data class Recipe(
    val kind: String,
    val external_id: String,
    val published_at: LocalDateTime,
    val publisher: String,
    val title: String,
    val description: String,
    val thumbnails: Any
)

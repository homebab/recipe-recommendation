package com.homebab.reciperecommender

import java.time.LocalDateTime


data class Hits(
    val _index: String,
    val _type: String,
    val _id: String,
    val _score: Number,
    val _source: Any
)

data class HitsWrapper(
    val total: Any,
    val max_score: Number,
    val hits: List<Hits>
)

data class ESResponse(
    val took: Number,
    val timed_out: Boolean,
    val _shards: Any,
    val hits: HitsWrapper
)

data class RecipeTemp(val name: String, val description: String)

data class Recipe(
    val kind: String,
    val external_id: String,
    val published_at: LocalDateTime,
    val publisher: String,
    val title: String,
    val description: String,
    val thumbnails: Any
)

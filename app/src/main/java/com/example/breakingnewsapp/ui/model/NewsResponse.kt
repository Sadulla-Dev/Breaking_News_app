package com.example.breakingnewsapp.ui.model

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
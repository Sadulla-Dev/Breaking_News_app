package com.example.breakingnewsapp.ui.viewModel

import com.example.breakingnewsapp.ui.api.RetrofitInstance
import com.example.breakingnewsapp.ui.db.ArticleDatabase
import com.example.breakingnewsapp.ui.model.Article
import retrofit2.Retrofit

class NewsRepository(
    val db : ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String,pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String,pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery,pageNumber)

    suspend fun unsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}
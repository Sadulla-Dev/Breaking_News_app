package com.example.breakingnewsapp.ui.viewModel

import com.example.breakingnewsapp.ui.api.RetrofitInstance
import com.example.breakingnewsapp.ui.db.ArticleDatabase
import retrofit2.Retrofit

class NewsRepository(
    val db : ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String,pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

}
package com.example.breakingnewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.breakingnewsapp.R
import com.example.breakingnewsapp.ui.db.ArticleDatabase
import com.example.breakingnewsapp.ui.viewModel.NewsRepository
import com.example.breakingnewsapp.ui.viewModel.NewsViewModel
import com.example.breakingnewsapp.ui.viewModel.NewsViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

//        bottomNavigationView.setupWithNavController(newNavHostFragment.findNavController())
        val navHostFragment= supportFragmentManager.findFragmentById(R.id.newNavHostFragment) as NavHostFragment
        val navController= navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}
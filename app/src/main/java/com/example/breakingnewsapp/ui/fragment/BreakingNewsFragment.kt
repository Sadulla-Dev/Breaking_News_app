package com.example.breakingnewsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingnewsapp.R
import com.example.breakingnewsapp.ui.NewsActivity
import com.example.breakingnewsapp.ui.adapter.NewsAdapter
import com.example.breakingnewsapp.ui.viewModel.NewsViewModel
import com.example.breakingnewsapp.ui.utils.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "BreakingNewsFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        setUpRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)

                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                         Log.e(TAG,"An error occured: $message")

                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }
}
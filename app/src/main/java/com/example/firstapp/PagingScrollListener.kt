package com.example.firstapp

import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.utils.Constants

abstract class PagingScrollListener: RecyclerView.OnScrollListener() {
    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    private var isScrolling = false

    private val pageLimit: Int = Constants.kPageSize


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        onScroll(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount

        val isNotLoadingAndNotLastPage = !isLoading() && !isLastPage()
        val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
        val isNotAtBeginning = firstVisibleItemPosition >= 0
        val isTotalMoreThanVisible = totalItemCount >= pageLimit
        val shouldPaginate =
            isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
        if (shouldPaginate) {
            loadMoreItems()
            isScrolling = false
        }

    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true
        }
        onScrollStateChange(recyclerView, newState)
    }

    abstract fun loadMoreItems()

    abstract fun onScroll(recyclerView: RecyclerView, dx: Int, dy: Int)
    abstract fun onScrollStateChange(recyclerView: RecyclerView, newState: Int)
}
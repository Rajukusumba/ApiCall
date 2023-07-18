package com.example.firstapp

import com.example.firstapp.utils.Constants.kInitialPage
import com.example.firstapp.utils.Constants.kPageSize

interface paging {

    var pageIndex: Int
    var isLoading: Boolean
    var isLastPage: Boolean

    fun fetchData(arg: Any? = null)

    fun resetPaging() {
        pageIndex = kInitialPage
        isLastPage = false
        isLoading = false
    }

    fun resetPagingAndFetch() {
        pageIndex = kInitialPage
        isLastPage = false
        isLoading = false
        fetchData()
    }

    fun getPageSize(): Int {
        return if (pageIndex == kInitialPage) kPageSize else kPageSize
    }
}
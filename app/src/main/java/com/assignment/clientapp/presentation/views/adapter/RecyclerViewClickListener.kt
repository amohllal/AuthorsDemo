package com.assignment.clientapp.presentation.views.adapter

import com.assignment.domain.model.AuthorsDomainResponseItem

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(author: AuthorsDomainResponseItem)

}
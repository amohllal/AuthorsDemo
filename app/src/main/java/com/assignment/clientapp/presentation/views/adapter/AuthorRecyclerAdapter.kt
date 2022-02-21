package com.assignment.clientapp.presentation.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.clientapp.R
import com.assignment.clientapp.databinding.AuthorItemBinding
import com.assignment.domain.model.AuthorsDomainResponseItem
import com.bumptech.glide.Glide

class AuthorRecyclerAdapter(
    private val authorList: List<AuthorsDomainResponseItem>,
    private val context: Context,
    private val itemClick: (AuthorsDomainResponseItem) -> Unit

) : RecyclerView.Adapter<AuthorRecyclerAdapter.AuthorViewHolder>() {

    override fun getItemCount() = authorList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AuthorViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.author_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val author = authorList[position]

        holder.recyclerviewPostBinding.authorNameTv.text = author.name
        holder.recyclerviewPostBinding.authorUsernameTv.text = author.userName
        holder.recyclerviewPostBinding.authorEmailTv.text = author.email
        Glide.with(context)
            .load(author.avatarUrl)
            .into(holder.recyclerviewPostBinding.authorIv)

        holder.recyclerviewPostBinding.root.setOnClickListener {
           itemClick.invoke(author)
        }
    }


    inner class AuthorViewHolder(
        val recyclerviewPostBinding: AuthorItemBinding
    ) : RecyclerView.ViewHolder(recyclerviewPostBinding.root)

}
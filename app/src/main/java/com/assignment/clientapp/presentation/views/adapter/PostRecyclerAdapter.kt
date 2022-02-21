package com.assignment.clientapp.presentation.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.clientapp.R
import com.assignment.clientapp.databinding.PostsItemBinding
import com.assignment.domain.model.PostsDomainResponseItem
import com.bumptech.glide.Glide

class PostRecyclerAdapter(
    private val postList: List<PostsDomainResponseItem>,
    private val context: Context
) : RecyclerView.Adapter<PostRecyclerAdapter.PostsViewHolder>() {

    override fun getItemCount() = postList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.posts_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = postList[position]

        holder.recyclerviewPostBinding.postTitleTv.text = post.title
        holder.recyclerviewPostBinding.postDateTv.text =
            post.date?.replace("T", " ")?.replace("Z", "")
        holder.recyclerviewPostBinding.postBodyTv.text = post.body
        Glide.with(context)
            .load(post.imageUrl)
            .into(holder.recyclerviewPostBinding.postIv)


    }


    inner class PostsViewHolder(
        val recyclerviewPostBinding: PostsItemBinding
    ) : RecyclerView.ViewHolder(recyclerviewPostBinding.root)

}
package com.assignment.clientapp.presentation.views.ui.frgaments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.assignment.clientapp.R
import com.assignment.clientapp.databinding.FragmentPostsBinding
import com.assignment.clientapp.presentation.core.Connectivity
import com.assignment.clientapp.presentation.core.wrapper.DataStatus
import com.assignment.clientapp.presentation.viewmodel.AuthorViewModel
import com.assignment.clientapp.presentation.views.adapter.PostRecyclerAdapter
import com.assignment.domain.model.AuthorsDomainResponseItem
import com.assignment.domain.model.PostsDomainResponseItem
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_posts.*


@AndroidEntryPoint
class PostsFragment : Fragment() {

    private lateinit var authorModel: AuthorsDomainResponseItem
    private lateinit var postAdapter: PostRecyclerAdapter
    private val authorViewModel: AuthorViewModel by viewModels()
    private var postList = ArrayList<PostsDomainResponseItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentPostsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_posts, null, false)
        val view = binding.root
        authorModel = arguments?.getSerializable("author") as AuthorsDomainResponseItem
        binding.author = authorModel
        Glide.with(requireContext())
            .load(authorModel.avatarUrl)
            .into(binding.authorIv)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postAdapter = PostRecyclerAdapter(postList, requireContext())
        posts_rv.adapter = postAdapter
        getPosts(authorModel.id)
    }


    private fun getPosts(id: Int?) {
        if (Connectivity.isOnline(requireContext())) {
            authorViewModel.getPostsForAuthor(id.toString())
        } else {
            hideLoading()
            Toast.makeText(
                requireContext(),
                "please check your internet connection",
                Toast.LENGTH_LONG
            ).show()
        }
        authorViewModel.postsLiveData.observe(requireActivity(), {
            when (it?.status) {
                DataStatus.Status.LOADING -> showLoading()
                DataStatus.Status.SUCCESS -> handleSuccessData(it.data)
                DataStatus.Status.ERROR -> showError()
            }
        })

    }

    private fun showError() {
        hideLoading()
        posts_rv.visibility = View.GONE
        Toast.makeText(
            requireContext(),
            "Some Error Occurred, please try again later..",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun handleSuccessData(data: List<PostsDomainResponseItem>?) {
        hideLoading()
        if (data.isNullOrEmpty()) {
            Toast.makeText(
                requireContext(),
                "This author doesn't have any posts",
                Toast.LENGTH_LONG
            ).show()
        } else {
            postList.addAll(data)
            posts_rv.visibility = View.VISIBLE
            postAdapter.notifyDataSetChanged()
        }
    }

    private fun hideLoading() {
        progress_loading.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        progress_loading.visibility = View.VISIBLE
        posts_rv.visibility = View.INVISIBLE
    }


}
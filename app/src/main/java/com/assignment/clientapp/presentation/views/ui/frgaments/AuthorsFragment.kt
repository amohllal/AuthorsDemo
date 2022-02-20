package com.assignment.clientapp.presentation.views.ui.frgaments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.assignment.clientapp.R
import com.assignment.clientapp.presentation.core.Connectivity
import com.assignment.clientapp.presentation.core.wrapper.DataStatus
import com.assignment.clientapp.presentation.viewmodel.AuthorViewModel
import com.assignment.clientapp.presentation.views.adapter.AuthorRecyclerAdapter
import com.assignment.domain.model.AuthorsDomainResponseItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_authors.*


@AndroidEntryPoint
class AuthorsFragment : Fragment() {

    private lateinit var authorAdapter: AuthorRecyclerAdapter
    private val authorViewModel: AuthorViewModel by viewModels()
    private var authorList = ArrayList<AuthorsDomainResponseItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authorAdapter = AuthorRecyclerAdapter(authorList, requireContext()) {
            val bundle = bundleOf("author" to it)
            view.findNavController()
                .navigate(R.id.navigate_authorsFragment_to_postsFragment, bundle)
        }
        author_rv.adapter = authorAdapter
        getAuthorsList()


    }

    private fun getAuthorsList() {
        if (Connectivity.isOnline(requireContext())) {
            authorViewModel.getAuthorsList()
        } else {
            authorViewModel.getAuthorsFromStorage()
        }
        authorViewModel.authorsLiveData.observe(requireActivity(), {
            when (it?.status) {
                DataStatus.Status.LOADING -> showLoading()
                DataStatus.Status.SUCCESS -> handleSuccessData(it.data)
                DataStatus.Status.ERROR -> showError()
            }
        })
    }

    private fun handleSuccessData(data: List<AuthorsDomainResponseItem>?) {
        hideLoading()
        if (data.isNullOrEmpty()) {
            author_rv.visibility = View.GONE
            no_list_found_tv.visibility = View.VISIBLE
        } else {
            author_rv.visibility = View.VISIBLE
            authorList.addAll(data)
            authorAdapter.notifyDataSetChanged()

        }
    }

    private fun showLoading() {
        progress_loading.visibility = View.VISIBLE
        author_rv.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        progress_loading.visibility = View.INVISIBLE
    }


    private fun showError() {
        hideLoading()
        author_rv.visibility = View.GONE
        Toast.makeText(
            requireContext(),
            "Some Error Occurred, please try again later..",
            Toast.LENGTH_LONG
        ).show()
    }


}
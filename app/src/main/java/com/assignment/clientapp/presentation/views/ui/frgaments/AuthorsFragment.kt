package com.assignment.clientapp.presentation.views.ui.frgaments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.assignment.clientapp.R
import com.assignment.clientapp.presentation.core.Connectivity
import com.assignment.clientapp.presentation.core.wrapper.DataStatus
import com.assignment.clientapp.presentation.viewmodel.AuthorViewModel
import com.assignment.clientapp.presentation.views.adapter.AuthorRecyclerAdapter
import com.assignment.clientapp.presentation.views.ui.activities.MainActivity
import com.assignment.domain.model.AuthorsDomainResponse
import com.assignment.domain.model.AuthorsDomainResponseItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_authors.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex


@AndroidEntryPoint
class AuthorsFragment : Fragment() {

    private var authorAdapter: AuthorRecyclerAdapter? = null
    private val authorViewModel: AuthorViewModel by viewModels()
    private var authorList = arrayListOf<AuthorsDomainResponseItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authors, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorAdapter = AuthorRecyclerAdapter(authorList, requireContext()) {
            val bundle = bundleOf("author" to it)
            view.findNavController()
                .navigate(R.id.navigate_authorsFragment_to_postsFragment, bundle)
        }
        author_rv.adapter = authorAdapter
        getAuthors()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getAuthors(){
        if (Connectivity.isOnline(requireContext())) {
            getAuthorsChannel()
        } else {
            getAuthorsStorageChannels()
        }
    }

    private fun getAuthorsChannel() {
        lifecycleScope.launch {
            authorViewModel.getAuthorsListUsingChannel()
            val authorReceiverChannel = authorViewModel.authorSenderChannel.receive()
            for (i in authorReceiverChannel.authorsDomainResponse!!) {
                authorList.add(i)
            }
            handleSuccessData(authorList)
        }
    }

    private fun getAuthorsStorageChannels() {
        authorViewModel.getAuthorsFromStorage()
        lifecycleScope.launch {
            val authorListReceiver = authorViewModel.authorActor.receive()
            handleSuccessData(authorListReceiver)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleSuccessData(data: List<AuthorsDomainResponseItem>?) {
        if (data.isNullOrEmpty()) {
            author_rv.visibility = View.GONE
            no_list_found_tv.visibility = View.VISIBLE
        } else {
            author_rv.visibility = View.VISIBLE
            authorList.addAll(data)
            authorAdapter?.notifyDataSetChanged()

        }
    }

    override fun onDestroyView() {
        authorAdapter = null
        author_rv.adapter = null
        super.onDestroyView()

    }


}
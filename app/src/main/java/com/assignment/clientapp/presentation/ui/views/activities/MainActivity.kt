package com.assignment.clientapp.presentation.ui.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.assignment.clientapp.R
import com.assignment.clientapp.presentation.viewmodel.AuthorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val authorViewModel: AuthorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authorViewModel.getAuthorsList()

    }
}
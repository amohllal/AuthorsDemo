package com.assignment.clientapp.presentation.views.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.assignment.clientapp.R
import com.assignment.clientapp.presentation.core.NotificationModel
import com.assignment.clientapp.presentation.core.makeNotification

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.fragmentContainerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(home_toolbar.toolbar)


        intent?.let{ navController.handleDeepLink(it)}
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.let{ navController.handleDeepLink(it)}

    }
}
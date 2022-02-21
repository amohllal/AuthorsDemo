package com.assignment.clientapp.presentation.views.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assignment.clientapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(home_toolbar.toolbar)
    }

    fun changeToolbarTitle(title: String) {
        home_toolbar.toolbar.title_toolbar.text = title
    }
}
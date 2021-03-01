package com.mobile.programming.studentview.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import com.mobile.programming.studentview.R
import com.mobile.programming.studentview.databinding.ActivityAuthBinding


class AuthActivity : AppCompatActivity() {

    private lateinit var authBinding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authBinding = ActivityAuthBinding.inflate(layoutInflater)
        val view = authBinding.root
        setContentView(view)
        setSupportActionBar(authBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        
    }
}
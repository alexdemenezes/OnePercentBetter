package com.example.onepercentbetter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.onepercentbetter.databinding.ActivityMainBinding
import com.example.onepercentbetter.databinding.FragmentGetStartedBinding

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        setupNavigation()
//    }
//
//    private fun setupNavigation() {
//        val navController = findNavController(R.id.nav_host_fragment_container)
//        binding.bottomNavBar.setupWithNavController(navController)
//    }

    private lateinit var binding: FragmentGetStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}
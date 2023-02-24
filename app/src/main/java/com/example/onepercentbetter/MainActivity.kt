package com.example.onepercentbetter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.onepercentbetter.databinding.ActivityMainBinding
import com.example.onepercentbetter.databinding.FragmentMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        setContentView(binding.root)

        viewModel.loggedIn.observe(this) {
            if (it == true) {
                findNavController(R.id.start_nav_host_fragment).navigate(R.id.mainFragment)
            } else if (it == false) {
                findNavController(R.id.start_nav_host_fragment).navigate(R.id.auth)
            }
        }
        viewModel.checkLoggedInState()
    }


//    private lateinit var binding: FragmentMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = FragmentMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }

}
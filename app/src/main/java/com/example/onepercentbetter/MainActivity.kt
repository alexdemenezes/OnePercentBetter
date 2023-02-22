package com.example.onepercentbetter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.onepercentbetter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

//    private lateinit var binding: FragmentRegisterBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = FragmentRegisterBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }

}
package com.example.onepercentbetter.presenter.screens.getStarted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onepercentbetter.databinding.FragmentGetStartedBinding

class GetStartedFragment: Fragment() {
    private lateinit var binding: FragmentGetStartedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetStartedBinding.inflate(inflater, container, false)
        return binding.root
    }
}
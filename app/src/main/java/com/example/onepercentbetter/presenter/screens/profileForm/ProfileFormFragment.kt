package com.example.onepercentbetter.presenter.screens.profileForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onepercentbetter.databinding.FragmentProfileFormBinding


class ProfileFormFragment: Fragment() {
    private lateinit var binding: FragmentProfileFormBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileFormBinding.inflate(inflater, container, false)
        return binding.root
    }
}
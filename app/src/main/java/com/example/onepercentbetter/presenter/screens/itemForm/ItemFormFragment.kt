package com.example.onepercentbetter.presenter.screens.itemForm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onepercentbetter.databinding.FragmentItemFormBinding

class ItemFormFragment: Fragment() {
    private lateinit var binding: FragmentItemFormBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemFormBinding.inflate(inflater, container, false)
        return binding.root
    }
}
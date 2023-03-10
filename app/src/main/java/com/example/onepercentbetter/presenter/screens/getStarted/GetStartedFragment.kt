package com.example.onepercentbetter.presenter.screens.getStarted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(GetStartedFragmentDirections.actionGetStartedFragmentToLoginFragment())
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(GetStartedFragmentDirections.actionGetStartedFragmentToRegisterFragment())
        }
    }
}
package com.example.onepercentbetter.presenter.screens.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.onepercentbetter.R
import com.example.onepercentbetter.databinding.FragmentRegisterBinding

class RegisterFragment: Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        binding.btnRegister.setOnClickListener {
            val fullName = binding.editTextFullName.text.toString()
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextPassword.text.toString()
            val isFormValid = validateForm()
            if (isFormValid) {
                viewModel.setUserData(fullName, email, password)
                viewModel.registerUser()
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
        }
    }

    private fun validateForm(): Boolean {
        return validateFullName() && validateEmail() && validatePassword()
    }

    private fun validateFullName(): Boolean {
        val fullName = binding.editTextFullName.text.toString()
        return if (fullName.isEmpty()) {
            binding.editTextFullName.error = getString(R.string.empty_field)
            false
        } else {
            binding.editTextFullName.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        val email = binding.editTextTextEmailAddress.text.toString()
        return if(email.isEmpty()) {
            binding.editTextTextEmailAddress.error = getString(R.string.empty_field)
            false
        } else {
             binding.editTextTextEmailAddress.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.editTextPassword.text.toString()
        return if (password.isEmpty()) {
            binding.editTextPassword.error = getString(R.string.empty_field)
            false
        } else {
            binding.editTextPassword.error = null
            true
        }
    }

}
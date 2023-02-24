package com.example.onepercentbetter.presenter.screens.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.onepercentbetter.R
import com.example.onepercentbetter.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(viewLifecycleOwner) {
            Log.d("LoginFragment", "entrou no observe, valor: ${it.loggedIn}")
            if (it.loggedIn) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
            }
        }

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextPassword.text.toString()
            val isFormValid = validateForm()
            if (isFormValid) {
                viewModel.setUserData(email, password)
                viewModel.loginUser()
            }
        }
    }

    private fun validateForm(): Boolean {
        return validateEmail() && validatePassword()
    }

    private fun validateEmail(): Boolean {
        val email = binding.editTextTextEmailAddress.text.toString()
        return if (email.isEmpty()) {
            binding.editTextTextEmailAddress.error = getString(R.string.empty_field)
            false
        } else {
            binding.editTextTextEmailAddress.error = null
            true
        }
    }

    private fun validatePassword() : Boolean {
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
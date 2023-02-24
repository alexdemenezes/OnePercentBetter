package com.example.onepercentbetter.presenter.screens.loading

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.onepercentbetter.MainActivityViewModel
import com.example.onepercentbetter.data.database.AppDatabase
import com.example.onepercentbetter.data.repository.ItemRepositoryImpl
import com.example.onepercentbetter.databinding.FragmentLoadingBinding
import com.example.onepercentbetter.databinding.FragmentLoginBinding
import com.example.onepercentbetter.domain.usecase.item.GetItemsByTodayUseCaseImpl
import com.example.onepercentbetter.presenter.screens.home.HomeViewModel
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class LoadingFragment: Fragment() {
    private lateinit var binding: FragmentLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onResume() {
//        super.onResume()
//        Executors.newSingleThreadScheduledExecutor().schedule({
//            findNavController().navigate(LoadingFragmentDirections.actionLoadingFragmentToAuth())
//        }, 3, TimeUnit.SECONDS)
//
//    }
}
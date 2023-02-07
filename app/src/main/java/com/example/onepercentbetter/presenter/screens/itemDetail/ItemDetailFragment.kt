package com.example.onepercentbetter.presenter.screens.itemDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onepercentbetter.databinding.FragmentItemDetailBinding

class ItemDetailFragment: Fragment() {
    private lateinit var binding: FragmentItemDetailBinding

    private val args: ItemDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editButton.setOnClickListener {
            onEditButton()
        }
    }

    private fun onEditButton() {
        val action = ItemDetailFragmentDirections.actionItemDetailFragmentToItemFormFragment().setItemId(args.itemId)
        findNavController().navigate(action)
    }
}
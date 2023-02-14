package com.example.onepercentbetter.presenter.screens.itemDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onepercentbetter.R
import com.example.onepercentbetter.data.database.AppDatabase
import com.example.onepercentbetter.data.repository.ItemRepositoryImpl
import com.example.onepercentbetter.databinding.FragmentItemDetailBinding
import com.example.onepercentbetter.domain.model.item.Item
import com.example.onepercentbetter.domain.model.item.ItemStatus
import com.example.onepercentbetter.domain.usecase.item.GetItemByIdUseCaseImpl
import com.example.onepercentbetter.domain.usecase.item.GetItemsByTodayUseCaseImpl

class ItemDetailFragment: Fragment() {
    private lateinit var binding: FragmentItemDetailBinding
    private val viewModel: ItemDetailViewModel by activityViewModels {
        val appDatabase = AppDatabase.getInstance(this.requireContext())
        val itemRepository = ItemRepositoryImpl(appDatabase)
        val getItemByIdUseCase = GetItemByIdUseCaseImpl(itemRepository)
        ItemDetailViewModel.Factory(getItemByIdUseCase)
    }

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

        viewModel.streamUiState().observe(viewLifecycleOwner) {state ->
            state.item?.let {
                bindUiState(it)
            }
        }

        viewModel.setItemId(args.itemId)
        viewModel.getItem()
    }

    private fun bindUiState(item: Item) {
        if (item.status == ItemStatus.LEARNED) {
            binding.ivStatusIcon.setImageResource(R.drawable.ic_book)
        } else {
            binding.ivStatusIcon.setImageResource(R.drawable.ic_tool)
        }
        binding.tvStatus.text = item.status.description
        binding.tvTitleValue.text = item.title
        binding.tvDescriptionValue.text = item.description
        binding.tvDifficultyLevelValue.text = item.difficulty.description
        binding.tvDateValue.text = item.createdAt
    }

    private fun onEditButton() {
        val action = ItemDetailFragmentDirections.actionItemDetailFragmentToItemFormFragment().setItemId(args.itemId)
        findNavController().navigate(action)
    }
}
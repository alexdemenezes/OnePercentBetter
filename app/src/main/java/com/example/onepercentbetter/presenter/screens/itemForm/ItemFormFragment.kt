package com.example.onepercentbetter.presenter.screens.itemForm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.onepercentbetter.R
import com.example.onepercentbetter.data.database.AppDatabase
import com.example.onepercentbetter.data.repository.ItemRepositoryImpl
import com.example.onepercentbetter.databinding.FragmentItemFormBinding
import com.example.onepercentbetter.domain.model.item.Item
import com.example.onepercentbetter.domain.model.item.ItemDifficulty
import com.example.onepercentbetter.domain.model.item.ItemStatus
import com.example.onepercentbetter.domain.usecase.item.GetItemByIdUseCaseImpl


class ItemFormFragment: Fragment() {
    private lateinit var binding: FragmentItemFormBinding

    private val args: ItemFormFragmentArgs by navArgs()

    private val viewModel: ItemFormViewModel by activityViewModels {
        val appDatabase = AppDatabase.getInstance(this.requireContext())
        val itemRepository = ItemRepositoryImpl(appDatabase)
        val getItemByIdUseCase = GetItemByIdUseCaseImpl(itemRepository)

        ItemFormViewModel.Factory(args.itemId, getItemByIdUseCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.streamUiState().observe(viewLifecycleOwner) { state ->
            state.item?.let {
                if (it.title.isNotEmpty()) {
                    bindUiState(it)
                }
            }

            if (state.showInvalidFormMessage) {
                Log.d("ItemFormFragment", "solicitou mostrar mensagem")
                showInvalidFormMessage()
                viewModel.doneShowingInvalidFormMessage()
            }
        }

        binding.Easy.setOnClickListener {  onDifficultyHandleClick(it) }
        binding.Medium.setOnClickListener { onDifficultyHandleClick(it) }
        binding.Hard.setOnClickListener { onDifficultyHandleClick(it) }

        binding.Learned.setOnClickListener { onStatusHandleClick(it) }
        binding.Improved.setOnClickListener { onStatusHandleClick(it) }

        binding.btnSave.setOnClickListener { onSave() }
    }

    private fun bindUiState(item: Item) {
        // fill form with item info
    }

    private fun onDifficultyHandleClick(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when(view.id) {
                R.id.Easy -> if (checked) viewModel.setItemDifficult(ItemDifficulty.EASY)
                R.id.Medium -> if (checked) viewModel.setItemDifficult(ItemDifficulty.MEDIUM)
                R.id.Hard -> if (checked) viewModel.setItemDifficult(ItemDifficulty.HARD)
            }
        }
    }

    private fun onStatusHandleClick(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when(view.id) {
                R.id.Learned -> if (checked) viewModel.setItemStatus(ItemStatus.LEARNED)
                R.id.Improved -> if (checked) viewModel.setItemStatus(ItemStatus.IMPROVED)
            }
        }
    }

    private fun onSave() {
        viewModel.setItemTitle(binding.textInputLayoutTitle.editText?.text.toString())
        viewModel.setItemDescription(binding.textInputLayoutDescription.editText?.text.toString())

        // call fun to validate
        if (viewModel.isValidForm()) {
            // call fun to save, show success message and navigate
        }

        // if not: show error message
    }

    private fun showInvalidFormMessage() {
        Log.d("ItemFormFragment", "show message")
        val message = "É necessário adicionar um título."
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
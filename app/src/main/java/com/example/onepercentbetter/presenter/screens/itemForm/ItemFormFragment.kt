package com.example.onepercentbetter.presenter.screens.itemForm

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onepercentbetter.R
import com.example.onepercentbetter.data.database.AppDatabase
import com.example.onepercentbetter.data.repository.ItemFirestoreRepositoryImpl
import com.example.onepercentbetter.data.repository.ItemRepositoryImpl
import com.example.onepercentbetter.databinding.FragmentItemFormBinding
import com.example.onepercentbetter.domain.model.item.Item
import com.example.onepercentbetter.domain.model.item.ItemDifficulty
import com.example.onepercentbetter.domain.model.item.ItemStatus
import com.example.onepercentbetter.domain.usecase.item.GetItemByIdUseCaseImpl
import com.example.onepercentbetter.domain.usecase.item.SaveItemUseCaseImpl
import com.example.onepercentbetter.domain.usecase.item.UpdateItemUseCaseImpl
import com.google.android.material.snackbar.Snackbar


class ItemFormFragment: Fragment() {
    private lateinit var binding: FragmentItemFormBinding

    private val args: ItemFormFragmentArgs by navArgs()

    private var newDiff: ItemDifficulty? = null
    private var newStatus: ItemStatus? = null

    private val viewModel: ItemFormViewModel by activityViewModels {
        val appDatabase = AppDatabase.getInstance(this.requireContext())
//        val itemRepository = ItemRepositoryImpl(appDatabase)
        val itemRepository = ItemFirestoreRepositoryImpl()
        val getItemByIdUseCase = GetItemByIdUseCaseImpl(itemRepository)
        val updateItemUseCase = UpdateItemUseCaseImpl(itemRepository)
        val saveItemUseCase = SaveItemUseCaseImpl(itemRepository)

        ItemFormViewModel.Factory(getItemByIdUseCase, saveItemUseCase, updateItemUseCase)
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

        viewModel.showSuccess.observe(viewLifecycleOwner) {
            if (it) {
                showSuccessSnackbar()
                viewModel.doneShowingSuccessSnackbar()
                findNavController().navigate(ItemFormFragmentDirections.actionItemFormFragmentToHomeFragment())
            }
        }

        viewModel.showFailure.observe(viewLifecycleOwner) {
            if (it) {
                showFailureSnackbar()
                viewModel.doneShowingFailureSnackbar()
            }
        }

        viewModel.getItemById(args.itemId)

        viewModel.item.observe(viewLifecycleOwner) {
            bindUiState(it)
        }

        binding.Easy.setOnClickListener {  onDifficultyHandleClick(it) }
        binding.Medium.setOnClickListener { onDifficultyHandleClick(it) }
        binding.Hard.setOnClickListener { onDifficultyHandleClick(it) }

        binding.Learned.setOnClickListener { onStatusHandleClick(it) }
        binding.Improved.setOnClickListener { onStatusHandleClick(it) }

        binding.btnSave.setOnClickListener { onSave() }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.restoreDefaultItem()
    }

    private fun bindUiState(item: Item) {
        binding.textInputLayoutTitle.editText?.text = Editable.Factory().newEditable(item.title)
        val checkedDifficultyId = binding.rgDifficulty.getChildAt(item.difficulty.ordinal).id
        binding.rgDifficulty.check(checkedDifficultyId)
        val checkedStatusId = binding.rgStatus.getChildAt(item.status.ordinal).id
        binding.rgStatus.check(checkedStatusId)
        binding.textInputLayoutDescription.editText?.text = Editable.Factory().newEditable(item.description)
    }

    private fun onDifficultyHandleClick(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when(view.id) {
                R.id.Easy -> if (checked) newDiff = ItemDifficulty.EASY
                R.id.Medium -> if (checked)newDiff = ItemDifficulty.MEDIUM
                R.id.Hard -> if (checked) newDiff = ItemDifficulty.HARD
            }
        }
    }

    private fun onStatusHandleClick(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when(view.id) {
                R.id.Learned -> if (checked)  newStatus = ItemStatus.LEARNED
                R.id.Improved -> if (checked) newStatus = ItemStatus.IMPROVED
            }
        }
    }

    private fun validateTitleForm() {
        val title = binding.editTextTitle.text.toString()
        if (title.isEmpty()) {
            binding.textInputLayoutTitle.error = getString(R.string.invalid_title)
        } else {
            binding.textInputLayoutTitle.error = null
        }
    }

    private fun onSave() {
        val title = binding.textInputLayoutTitle.editText?.text.toString()
        val description = binding.textInputLayoutDescription.editText?.text.toString()
        viewModel.setItemFields(Item(
            title = title,
            description = description,
            status = newStatus ?: viewModel.item.value!!.status,
            difficulty = newDiff ?: viewModel.item.value!!.difficulty
        ))
        validateTitleForm()
        if (binding.textInputLayoutTitle.error.isNullOrEmpty()) {
            viewModel.save()
        }
    }

    private fun showSuccessSnackbar() {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            getString(R.string.saved_message),
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(resources.getColor(R.color.grey))
            .show()
    }

    private fun showFailureSnackbar() {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            getString(R.string.failure_message),
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(resources.getColor(R.color.red))
            .show()
    }

}
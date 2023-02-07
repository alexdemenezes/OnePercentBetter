package com.example.onepercentbetter.presenter.screens.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onepercentbetter.data.database.AppDatabase
import com.example.onepercentbetter.data.database.dao.ItemDatabaseDao
import com.example.onepercentbetter.data.repository.ItemRepositoryImpl
import com.example.onepercentbetter.databinding.FragmentHomeBinding
import com.example.onepercentbetter.domain.usecase.item.GetItemsByTodayUseCaseImpl
import com.example.onepercentbetter.presenter.adapter.ItemListAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: ItemListAdapter

    private val viewModel: HomeViewModel by activityViewModels {
        val appDatabase = AppDatabase.getInstance(this.requireContext())
        val itemRepository = ItemRepositoryImpl(appDatabase)
        val getItemsByTodayUseCase = GetItemsByTodayUseCaseImpl(itemRepository)

        HomeViewModel.Factory(getItemsByTodayUseCase = getItemsByTodayUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemListAdapter(::onItemClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.RecyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToItemFormFragment())
        }

        viewModel.streamUiState().observe(viewLifecycleOwner) {state ->
            bindUiState(state)
        }
    }

    private fun bindUiState(state: HomeUiState) {
        adapter.updateItems(state.itemList)
    }

    private fun onItemClick(id: String) {
        findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToItemDetailFragment(id))
    }
}
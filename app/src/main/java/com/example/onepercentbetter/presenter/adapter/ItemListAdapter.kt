package com.example.onepercentbetter.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.onepercentbetter.R
import com.example.onepercentbetter.databinding.FragmentHomeBinding
import com.example.onepercentbetter.databinding.ItemBinding
import com.example.onepercentbetter.domain.model.item.Item
import com.example.onepercentbetter.domain.model.item.ItemDifficulty
import com.example.onepercentbetter.domain.model.item.ItemStatus

class ItemListAdapter(private val clickListener: (id: String) -> Unit) :
    RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<Item> =
            AsyncListDiffer(this, DiffCallback)

    init {
        val mockItems = listOf<Item>(
            Item(
                id = "example-1",
                title = "cozinhar gyoza",
                description = "",
                status = ItemStatus.LEARNED,
                difficulty = ItemDifficulty.MEDIUM,
                createdAt = "04/02/2023"
            ),
            Item(
                id = "example-2",
                title = "kotlin",
                description = "aprimorei kotlin",
                status = ItemStatus.IMPROVED,
                difficulty = ItemDifficulty.HARD,
                createdAt = "04/02/2023"
            )
        )
        asyncListDiffer.submitList(mockItems)
    }

    fun updateItems(items: List<Item>) {
        asyncListDiffer.submitList(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = asyncListDiffer.currentList[position]
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    class ViewHolder(
        private val binding: ItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item, clickListener: (id: String) -> Unit) {
            if (item.status == ItemStatus.LEARNED) {
                binding.ivLogo.setImageResource(R.drawable.ic_book)
            } else {
                binding.ivLogo.setImageResource(R.drawable.ic_tool)
            }
            binding.tvTitle.text = item.title
            binding.tvDifficulty.text = item.difficulty.description
            binding.root.setOnClickListener {
                clickListener(item.id)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}
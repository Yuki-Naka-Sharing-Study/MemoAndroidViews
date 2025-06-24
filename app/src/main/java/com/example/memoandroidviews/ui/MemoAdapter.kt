package com.example.memoandroidviews.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.memoandroidviews.data.Memo
import com.example.memoandroidviews.databinding.ItemMemoBinding

class MemoAdapter(
    private val onDeleteClicked: (Memo) -> Unit,
    private val onUpdateClicked: (Memo) -> Unit
) : ListAdapter<Memo, MemoAdapter.MemoViewHolder>(MemoDiffCallback()) {
    private val editingStates = mutableMapOf<Int, Boolean>()

    inner class MemoViewHolder(private val binding: ItemMemoBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(memo: Memo) {
            binding.memo = memo

            val isEditing = editingStates[memo.id] ?: false
            binding.isEditing = isEditing

            binding.onEditClicked = View.OnClickListener {
                editingStates[memo.id] = true
                notifyItemChanged(adapterPosition)
            }

            binding.onSaveClicked = View.OnClickListener {
                editingStates[memo.id] = false
                onUpdateClicked(memo)
                notifyItemChanged(adapterPosition)
            }

            binding.onDeleteClicked = View.OnClickListener {
                onDeleteClicked(memo)
            }

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val binding = ItemMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val item = getItem(position)
        Log.d("MemoAdapter", "Binding item: id=${item.id}, content=${item.content}")
        holder.bind(item)
    }

    class MemoDiffCallback : DiffUtil.ItemCallback<Memo>() {
        override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.content == newItem.content &&
                    oldItem.timestamp == newItem.timestamp
        }

        override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem == newItem
        }
    }
}

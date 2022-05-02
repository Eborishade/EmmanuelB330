package com.example.worddictionary.activities.words

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worddictionary.database.Word
import com.example.worddictionary.databinding.FragmentWordItemBinding


class WordDictAdapter(private val onClickListener: OnClickListener ) : ListAdapter<Word,
        WordDictAdapter.WordViewHolder>(DiffCallback) {
    class WordViewHolder(
        private var binding: FragmentWordItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word) {
            binding.word = word
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(FragmentWordItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = getItem(position)
        holder.bind(word)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(word)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.wordId == newItem.wordId
        }
    }

    class OnClickListener(val clickListener: (word: Word) -> Unit) {
        fun onClick(word: Word) = clickListener(word)
    }
}
package com.example.allegrointernmobile.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.allegrointernmobile.R
import com.example.allegrointernmobile.databinding.RepositoryItemBinding
import com.example.allegrointernmobile.model.RepositoryInfo

class RepositoryListAdapter internal constructor(private val mListener: ReposItemClickListener):
    ListAdapter<RepositoryInfo, RepositoryListAdapter.RepositoryInfoViewHolder>(DiffCallback) {

    class RepositoryInfoViewHolder(
        private var binding: RepositoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repositoryInfo: RepositoryInfo, listener: ReposItemClickListener) {
            binding.repo = repositoryInfo
            binding.clickListener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RepositoryInfoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: RepositoryItemBinding = DataBindingUtil.inflate(
                    layoutInflater, R.layout.repository_item,
                    parent, false
                )
                return RepositoryInfoViewHolder(binding)

            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<RepositoryInfo>() {
        override fun areItemsTheSame(oldItem: RepositoryInfo, newItem: RepositoryInfo): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: RepositoryInfo, newItem: RepositoryInfo): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryInfoViewHolder {
        return RepositoryInfoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RepositoryInfoViewHolder, position: Int) {
        val repository = getItem(position)
        holder.bind(repository, mListener)
    }
}

interface ReposItemClickListener {
    fun chooseRepo(repo : RepositoryInfo) {Log.d("COKOLWIEK", repo.name)}
}
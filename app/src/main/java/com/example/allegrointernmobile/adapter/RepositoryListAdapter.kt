package com.example.allegrointernmobile.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.allegrointernmobile.R
import com.example.allegrointernmobile.databinding.LoadingItemBinding
import com.example.allegrointernmobile.databinding.RepositoryItemBinding
import com.example.allegrointernmobile.model.dao.RepositoryInfo

class RepositoryListAdapter internal constructor(private val mListener: ReposItemClickListener):
    ListAdapter<RepositoryInfo, RecyclerView.ViewHolder>(DiffCallback) {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

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

    class LoadingViewHolder(
        binding: LoadingItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): LoadingViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: LoadingItemBinding = DataBindingUtil.inflate(
                    layoutInflater, R.layout.loading_item,
                    parent, false
                )
                return LoadingViewHolder(binding)
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
    ): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            RepositoryInfoViewHolder.from(parent)
        } else {
            LoadingViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoryInfoViewHolder) {
            val repository = getItem(position)
            holder.bind(repository, mListener)
        }
        if (holder is LoadingViewHolder){
            showLoadingView(holder, position)
        }
    }

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) {
        // Progressbar would be displayed
    }

    override fun getItemViewType(position: Int): Int {
        if (getItem(position) == null){
            return VIEW_TYPE_LOADING
        }
        return VIEW_TYPE_ITEM
    }

    fun refreshRepositoriesList(){
        notifyDataSetChanged()
    }
}

interface ReposItemClickListener {
    fun chooseRepo(repo : RepositoryInfo) {Log.d("Repository", "Chosen repository: " + repo.name)}
}
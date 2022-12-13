package com.example.allegrointernmobile.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.allegrointernmobile.model.dao.RepositoryInfo

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<RepositoryInfo>?) {
    val adapter = recyclerView.adapter as RepositoryListAdapter
    adapter.submitList(data)
}
package com.abhay.acronym.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Decorating recycler view
 */
@BindingAdapter("dividerItemDecoration")
fun dividerItemDecoration(recyclerView: RecyclerView, showDivider: Boolean) {
    if (showDivider) {
        val dividerItemDecoration =
            DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}
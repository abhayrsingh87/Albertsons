package com.abhay.acronym.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abhay.acronym.model.LongForm
import com.abhay.acronym.R
import com.abhay.acronym.databinding.AcronymRecyclerViewItemLayoutBinding

class AcronymsListAdapter(
    private var acronymList: MutableList<LongForm>
) : RecyclerView.Adapter<AcronymsListAdapter.AcronymItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymItemViewHolder =
        AcronymItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.acronym_recycler_view_item_layout, parent, false
            )
        )

    override fun onBindViewHolder(holder: AcronymItemViewHolder, position: Int) {
        holder.bind(acronymList[position])
    }

    fun clearList() {
        acronymList.clear()
        notifyDataSetChanged()
    }

    fun updateList(list: List<LongForm>) {
        acronymList.clear()
        acronymList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return acronymList.size
    }

    class AcronymItemViewHolder(private val binding: AcronymRecyclerViewItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            longForm: LongForm
        ) {
            binding.longForm = longForm
        }
    }

}
package com.idmdragon.feature.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idmdragon.domain.model.Pexels
import com.idmdragon.feature.constant.ExtrasNameConstant
import com.idmdragon.feature.databinding.ItemGridBinding
import com.idmdragon.feature.ui.detail.DetailActivity

class GridAdapter (private val context: Context) : PagingDataAdapter<Pexels,GridAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Pexels>() {
        override fun areItemsTheSame(oldItem: Pexels, newItem: Pexels): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Pexels, newItem: Pexels): Boolean =
            oldItem.id == newItem.id
    }
)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter.ViewHolder {
        val itemBinding =
            ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: GridAdapter.ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(item = it) }
    }

    inner class ViewHolder(private val binding: ItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pexels) {
            with(binding) {
                itemView.setOnClickListener {
                    context.startActivity(
                        Intent(context, DetailActivity::class.java).putExtra(
                            ExtrasNameConstant.EXTRAS_ID,item.id))
                }
                Glide.with(itemView.context)
                    .load(item.smallImage)
                    .placeholder(ColorDrawable(Color.DKGRAY))
                    .into(ivPhoto)
            }
        }
    }
}
package com.idmdragon.feature.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.idmdragon.domain.model.Pexels
import com.idmdragon.feature.constant.ExtrasNameConstant.EXTRAS_ID
import com.idmdragon.feature.databinding.ItemListBinding
import com.idmdragon.feature.ui.detail.DetailActivity

class ListItemAdapter (private val context: Context)  : PagingDataAdapter<Pexels, ListItemAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Pexels>() {
        override fun areItemsTheSame(oldItem: Pexels, newItem: Pexels): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Pexels, newItem: Pexels): Boolean =
            oldItem.id == newItem.id
    }
)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemAdapter.ViewHolder {
        val itemBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListItemAdapter.ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pexels) {

            with(binding) {
                tvDesc.text = item.description
                binding.tvPhotographerName.text = item.photographer
                itemView.setOnClickListener {
                    context.startActivity(Intent(context,DetailActivity::class.java).putExtra(EXTRAS_ID,item.id))
                }

                Glide.with(itemView.context)
                    .load(item.smallImage)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .placeholder(ColorDrawable(Color.DKGRAY))
                    .apply(RequestOptions())
                    .into(ivPhoto)
            }
        }
    }
}
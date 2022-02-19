package com.idmdragon.feature.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idmdragon.domain.model.Pexels
import com.idmdragon.feature.constant.ExtrasNameConstant
import com.idmdragon.feature.databinding.ItemGridBinding
import com.idmdragon.feature.ui.detail.DetailActivity

class GridAdapter (private val context: Context) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    private val items = arrayListOf<Pexels>()

    fun setItems(items: List<Pexels>) {
        this.items.clear()
        this.items.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter.ViewHolder {
        val itemBinding =
            ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: GridAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

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
                    .load(item.mediumImage)
                    .into(ivPhoto)

            }
        }
    }
}
package com.idmdragon.feature.ui.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.idmdragon.domain.model.Pexels
import com.idmdragon.feature.R
import com.idmdragon.feature.databinding.ActivityDetailBinding
import com.idmdragon.feature.utils.DummyData

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        populateData(DummyData.generateItem())
    }

    private fun populateData(data: Pexels){
        with(binding){
            tvPhotographerName.text = data.photographer
            tvUrl.text = data.url
            ivColor.setBackgroundColor(Color.parseColor(data.color))
            Glide.with(this@DetailActivity)
                .load(data.originalImage)
                .into(ivPhoto)
        }
    }
}
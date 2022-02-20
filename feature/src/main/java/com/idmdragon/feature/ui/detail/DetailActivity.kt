package com.idmdragon.feature.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.utils.Resource
import com.idmdragon.feature.constant.ExtrasNameConstant.EXTRAS_ID
import com.idmdragon.feature.databinding.ActivityDetailBinding
import com.idmdragon.feature.di.featureModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(featureModule)
        setupListener()
        setupObserver()
    }

    private fun setupListener() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setupObserver() {
        viewModel.getPexelsById(intent.getIntExtra(EXTRAS_ID,1000)).observe(this) { resource ->
            when (resource) {
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    resource.data?.let { populateData(it) }
                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                    Snackbar.make(
                        binding.root,
                        resource.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun populateData(data: Pexels){
        with(binding){
            tvPhotographerName.text = data.photographer
            tvUrl.text = data.url
            tvPhotoName.text = data.description
            ivColor.setBackgroundColor(Color.parseColor(data.color))
            tvColor.text = data.color
            tvColor.setTextColor(Color.parseColor(data.color))
            Glide.with(this@DetailActivity)
                .load(data.originalImage)
                .placeholder(ColorDrawable(Color.DKGRAY))
                .into(ivPhoto)
        }
    }
}
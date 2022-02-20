package com.idmdragon.feature.ui.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                    resource.data?.let { populateData(it) }
                }
                is Resource.Loading -> {

                }

                is Resource.Error -> {

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
            ivColor.setBackgroundColor(Color.parseColor(data.color))
            Glide.with(this@DetailActivity)
                .load(data.originalImage)
                .into(ivPhoto)
        }
    }
}
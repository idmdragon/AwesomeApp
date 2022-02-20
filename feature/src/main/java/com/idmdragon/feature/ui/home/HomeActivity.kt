package com.idmdragon.feature.ui.home

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.utils.Resource
import com.idmdragon.feature.R
import com.idmdragon.awesomeapp.R as appR
import com.idmdragon.feature.databinding.ActivityHomeBinding
import com.idmdragon.feature.di.featureModule
import com.idmdragon.feature.ui.adapter.GridAdapter
import com.idmdragon.feature.ui.adapter.ListItemAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.context.loadKoinModules
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val listAdapter: ListItemAdapter = ListItemAdapter(this)
    private val gridAdapter: GridAdapter = GridAdapter(this)
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        loadKoinModules(featureModule)
        setContentView(binding.root)
        setupListAdapter()
        toolbar()
    }

    private fun toolbar() {
        binding.apply {
            collapsingToolbar.isTitleEnabled = false
            setSupportActionBar(toolbar)
            toolbar.inflateMenu(R.menu.menu)
            toolbar.elevation = 0F
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuGrid -> {
                setupGridAdapter()
            }
            else -> setupListAdapter()
        }
        return true
    }

    private fun setupListAdapter() {
        viewModel.getAllPexels().observe(this) { listItem ->
            lifecycleScope.launch {
                listAdapter.submitData(listItem)
                listAdapter.loadStateFlow.distinctUntilChanged()
                withContext(Dispatchers.Main){
                    binding.rvPexels.adapter = listAdapter
                    binding.rvPexels.itemAnimator = null
                    binding.rvPexels.layoutManager = LinearLayoutManager(this@HomeActivity)
                }
            }
        }
    }

    private fun setupGridAdapter() {
        viewModel.getAllPexels().observe(this) { listItem ->
            lifecycleScope.launch {
                gridAdapter.submitData(listItem)
                gridAdapter.loadStateFlow.distinctUntilChanged()
                withContext(Dispatchers.Main){
                    binding.rvPexels.itemAnimator = null
                    binding.rvPexels.layoutManager = GridLayoutManager(this@HomeActivity, 2)
                    binding.rvPexels.adapter = gridAdapter
                }
            }
        }
    }

}
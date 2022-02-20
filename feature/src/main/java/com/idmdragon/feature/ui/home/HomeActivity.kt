package com.idmdragon.feature.ui.home

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.feature.R
import com.idmdragon.feature.databinding.ActivityHomeBinding
import com.idmdragon.feature.di.featureModule
import com.idmdragon.feature.ui.adapter.GridAdapter
import com.idmdragon.feature.ui.adapter.ListItemAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


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
        isNoInternetConnection()
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

    private fun isNoInternetConnection(){
        if (!isNetworkConnected()){
            Snackbar.make(
                binding.root,
                getString(R.string.feature_msg_no_internet),
                Snackbar.LENGTH_LONG
            ).show()
        }else{
            setupListAdapter()
        }
    }

    @Suppress("DEPRECATION")
    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

}
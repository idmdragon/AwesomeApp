package com.idmdragon.feature.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
import org.koin.core.context.loadKoinModules
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val listAdapter: ListItemAdapter = ListItemAdapter(this)
    private val gridAdapter: GridAdapter = GridAdapter(this)
    private val viewModel: HomeViewModel by viewModel()
    private  var listPexels: List<Pexels>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        loadKoinModules(featureModule)
        setContentView(binding.root)
        toolbar()
        setupBar()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getAllPexels().observe(this) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listPexels = it }
                    setupListAdapter()
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


    private fun setupBar() {
        with(binding.collapsingToolbar){
            title = getString(appR.string.app_name)
            setCollapsedTitleTextColor(
                ContextCompat.getColor(this@HomeActivity, appR.color.black)
            )
            setExpandedTitleColor(
                ContextCompat.getColor(this@HomeActivity, appR.color.white)
            )
        }

    }

    private fun toolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbar.inflateMenu(R.menu.menu)
        binding.toolbar.elevation = 0F
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           R.id.menuGrid -> {setupGridAdapter()}
           else -> setupListAdapter()
       }
        return true
    }


    private fun setupListAdapter(){
        binding.rvPexels.adapter = listAdapter
        listPexels?.let { listAdapter.setItems(it) }
        binding.rvPexels.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
    }

    private fun setupGridAdapter(){
        listPexels?.let { gridAdapter.setItems(it) }
        binding.rvPexels.layoutManager = GridLayoutManager(this, 2)
        binding.rvPexels.adapter = gridAdapter

    }

}
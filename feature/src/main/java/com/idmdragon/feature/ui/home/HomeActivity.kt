package com.idmdragon.feature.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.idmdragon.feature.R
import com.idmdragon.awesomeapp.R as appR
import com.idmdragon.feature.databinding.ActivityHomeBinding
import com.idmdragon.feature.ui.adapter.GridAdapter
import com.idmdragon.feature.ui.adapter.ListItemAdapter
import com.idmdragon.feature.utils.DummyData


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val listAdapter: ListItemAdapter = ListItemAdapter(this)
    private val gridAdapter: GridAdapter = GridAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListAdapter()
        toolbar()
        setupBar()
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
        listAdapter.setItems(DummyData.generateList())
        binding.rvPexels.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
    }

    private fun setupGridAdapter(){
        gridAdapter.setItems(DummyData.generateList())
        binding.rvPexels.layoutManager = GridLayoutManager(this, 2)
        binding.rvPexels.adapter = gridAdapter

    }

}
package com.example.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val adapter = MenuItemAdapter()
    private val mainViewModel = MainViewModel(lifecycle)
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBindings()
        observeData()
    }

    private fun observeData() {
        mainViewModel.liveData.observe(this) {
            when (it) {
                ApiResult.Progress -> progressBar.visibility = View.VISIBLE
                is ApiResult.Error -> {
                    progressBar.visibility = View.GONE
                    Log.e("MainActivity", it.e.toString())
                    Toast.makeText(this, "Error fetching data", Toast.LENGTH_LONG).show()
                }
                is ApiResult.Success -> {
                    progressBar.visibility = View.GONE
                    adapter.setItems(it.data)
                }
            }
        }
    }

    private fun initBindings(){
        val popupMenu = PopupMenu(this, binding.bSelCity)
        popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            val id = menuItem.itemId
            when {
                id == R.id.moscow -> binding.bSelCity.text = "Москва"
                id == R.id.kazan -> binding.bSelCity.text = "Казань"
                id == R.id.sp -> binding.bSelCity.text = "Санкт-Петербург"
            }
            false
        }
        binding.bSelCity.setOnClickListener {
            popupMenu.show()
        }
        binding.bQrCode.setOnClickListener {
            Toast.makeText(this,"Включите камеру", Toast.LENGTH_LONG).show()
        }
        binding.apply {
            recyclerItems.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerItems.adapter = adapter
        }
        progressBar = binding.progressBar
    }
}
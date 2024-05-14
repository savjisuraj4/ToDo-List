package com.example.todolist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

@Suppress("DEPRECATION")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(applicationContext,AddTaskActivity::class.java))
        }
        try {

            val adapterClass = AdapterClass(applicationContext,  AsyncProcess().execute(Triple(applicationContext, null, "GET")).get())
            adapterClass.notifyDataSetChanged()
            binding.recyclerView.adapter = adapterClass

        } catch (e: Exception) {
            Log.d("Error", e.toString())
        }
    }
}
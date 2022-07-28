package com.example.petlist.activity

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.petlist.R
import com.example.petlist.adapter.PetAdapter

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<RecyclerView?>(R.id.recyclerView)?.apply {
            layoutManager = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
            adapter = PetAdapter(this@MainActivity)
        }
    }
}
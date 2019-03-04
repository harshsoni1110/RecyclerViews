package com.hari.recyclerviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)

        val list = ArrayList<RecyclerViewBean>()
        list.add(RecyclerViewBean("Item 1"))
        list.add(RecyclerViewBean("Item 2"))
        list.add(RecyclerViewBean("Item 3"))
        list.add(RecyclerViewBean("Item 4"))
        list.add(RecyclerViewBean("Item 5"))
        list.add(RecyclerViewBean("Item 6"))
        list.add(RecyclerViewBean("Item 7"))
        list.add(RecyclerViewBean("Item 8"))
        list.add(RecyclerViewBean("Item 9"))
        list.add(RecyclerViewBean("Item 10"))



        recyclerViewAdapter = RecyclerViewAdapter(list)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = linearLayoutManager
            adapter = recyclerViewAdapter
        }

    }
}

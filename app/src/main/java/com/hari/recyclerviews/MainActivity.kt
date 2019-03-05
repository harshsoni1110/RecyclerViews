package com.hari.recyclerviews

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hari.recyclerviews.recyclerAnimations.SlideInAnimator

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val list = ArrayList<RecyclerViewBean>()
    private var animator = SlideInAnimator(AccelerateDecelerateInterpolator())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)


        list.add(RecyclerViewBean("Item Data"))
        list.add(RecyclerViewBean("Item Data"))
        list.add(RecyclerViewBean("Item Data"))
        list.add(RecyclerViewBean("Item Data"))
        list.add(RecyclerViewBean("Item Data"))
        list.add(RecyclerViewBean("Item Data"))
        list.add(RecyclerViewBean("Item Data"))
        list.add(RecyclerViewBean("Item Data"))




        animator.addDuration = 500
        recyclerViewAdapter = RecyclerViewAdapter(list)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = linearLayoutManager
            adapter = recyclerViewAdapter
            itemAnimator = animator
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.recycler_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menuAdd -> {
                list.add(1, RecyclerViewBean("Item Data"))
                recyclerViewAdapter.notifyItemInserted(1)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

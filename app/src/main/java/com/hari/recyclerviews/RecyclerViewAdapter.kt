package com.hari.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerViewAdapter (private val myList : ArrayList<RecyclerViewBean>) : RecyclerView.Adapter <RecyclerViewAdapter.RcViewHolder>(){

    class RcViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return RcViewHolder(view)
    }

    override fun onBindViewHolder(holder: RcViewHolder, position: Int) {
        holder.itemView.txtItem.text = myList[position].itemName
    }

    override fun getItemCount(): Int {
        return myList.size
    }


}
package com.hari.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerViewAdapter (private val myList : ArrayList<RecyclerViewBean>) : RecyclerView.Adapter <RecyclerViewAdapter.RcViewHolder>(), StickyHeaderDecoration.StickyHeaderInterface{

    class RcViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        when (viewType){
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.header_item, parent, false)
            }
        }

        return RcViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return myList[position].type
    }
    override fun onBindViewHolder(holder: RcViewHolder, position: Int) {
        when (holder.itemViewType){
            0 -> {
                holder.itemView.txtItem.text = myList[position].itemName
            }
        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var tempPosition = itemPosition
        var headerPosition = 0
        do {
            if (this.isHeader(tempPosition)) {
                headerPosition = tempPosition
                break
            }
            tempPosition -= 1
        } while (tempPosition >= 0)
        return headerPosition
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return R.layout.header_item
    }

    override fun bindHeaderData(header: View, headerPosition: Int) {

    }

    override fun isHeader(itemPosition: Int): Boolean {
        return myList[itemPosition].type == 1
    }
}
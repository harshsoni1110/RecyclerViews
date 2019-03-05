package com.hari.recyclerviews

import androidx.recyclerview.widget.DiffUtil

class RecyclerBeanDiffCallBack : DiffUtil.ItemCallback<RecyclerViewBean>() {
    override fun areItemsTheSame(oldItem: RecyclerViewBean, newItem: RecyclerViewBean): Boolean {
        return oldItem.itemName == newItem.itemName
    }

    override fun areContentsTheSame(oldItem: RecyclerViewBean, newItem: RecyclerViewBean): Boolean {
        return oldItem == newItem
    }
}
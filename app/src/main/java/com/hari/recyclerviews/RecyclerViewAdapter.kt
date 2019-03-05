package com.hari.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hari.recyclerviews.databinding.HeaderItemBinding
import com.hari.recyclerviews.databinding.RecyclerItemBinding
import kotlinx.android.synthetic.main.header_item.view.*

class RecyclerViewAdapter : ListAdapter<RecyclerViewBean, RecyclerView.ViewHolder>(RecyclerBeanDiffCallBack()),
    StickyHeaderDecoration.StickyHeaderInterface {

    private class RcViewHolder(private val itemBinding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(bean: RecyclerViewBean) {
            itemBinding.item = bean
//            itemBinding.executePendingBindings()
        }
    }

    class RcHeaderViewHolder(private val headerItemBinding: HeaderItemBinding) :
        RecyclerView.ViewHolder(headerItemBinding.root) {
        fun bind(bean: RecyclerViewBean) {
            headerItemBinding.headerItem = bean
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            1 -> {
                val binding = HeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RcHeaderViewHolder(binding)
            }
            else -> {
                val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RcViewHolder(binding)
            }
        }


    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> {
                (holder as RcViewHolder).bind(getItem(position))
            }

            1 -> {
                (holder as RcHeaderViewHolder).bind(getItem(position))
            }
        }
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
        header.headerText.text = getItem(headerPosition).itemName
    }

    override fun isHeader(itemPosition: Int): Boolean {
        return getItem(itemPosition).type == 1
    }
}
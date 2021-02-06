package com.tatvasoft.apurv.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikhaellopez.hfrecyclerview.HFRecyclerView
import com.tatvasoft.apurv.R
import kotlinx.android.synthetic.main.row_user_item_full.view.*
import kotlinx.android.synthetic.main.row_user_item_half.view.*

class UserItemAdapter(private val items: List<String>) : HFRecyclerView<String>(true, false) { // With Header & With Footer

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val isOdd: Boolean = items.size % 2 != 0

        when (holder) {
            is ViewHolder.ItemViewHolder -> holder.bind(items, position,isOdd)
            is ViewHolder.HeaderViewHolder -> holder.bind(items,position, isOdd)
        }

//        if (items.size % 2 == 0) {
//            when (holder) {
//                is ViewHolder.ItemViewHolder -> holder.bind(items[position])
//
//            }
//        }else{
//            when (holder) {
//                is ViewHolder.ItemViewHolder -> holder.bind(items[position])
//                is ViewHolder.HeaderViewHolder -> holder.bind(items[position])
//            }
//        }
    }

    //region Override Get ViewHolder
    override fun getItemView(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder.ItemViewHolder(inflater.inflate(R.layout.row_user_item_half, parent, false))

    override fun getHeaderView(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder.HeaderViewHolder(inflater.inflate(R.layout.row_user_item_full, parent, false))

    override fun getFooterView(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder? = null

    //region ViewHolder Header and Footer
    sealed class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        class ItemViewHolder(view: View) : ViewHolder(view) {
            fun bind(item: List<String>, position: Int, isOdd: Boolean) {
                if(position < item.size - 1) {
                    var itemToLoad = ""
                    if (isOdd) {
                        itemToLoad = item[position + 1]
                    }
                    itemView.run {
                        Glide.with(itemView.context)
                            .load(itemToLoad)
                            .into(itemView.ivHalfItem)
                    }
                }
            }
        }

        class HeaderViewHolder(view: View) : ViewHolder(view) {
            fun bind(item: List<String>, position: Int, isOdd: Boolean) {
                if(isOdd && position == 0) {
                    itemView.run {
                        Glide.with(itemView.context)
                            .load(item)
                            .into(itemView.ivFullImageItem)
                    }
                }
            }
        }

        class FooterViewHolder(view: View) : ViewHolder(view)
    }
}

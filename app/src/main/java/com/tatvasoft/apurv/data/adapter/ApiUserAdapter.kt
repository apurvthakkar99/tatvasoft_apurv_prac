package com.tatvasoft.apurv.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tatvasoft.apurv.R
import com.tatvasoft.apurv.data.model.ApiUser
import kotlinx.android.synthetic.main.row_user.view.*


class ApiUserAdapter(
    private val users: ArrayList<ApiUser.Data.User>
) : RecyclerView.Adapter<ApiUserAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: ApiUser.Data.User, position: Int) {

            Glide.with(itemView.context)
                .load(user.image)
                .into(itemView.ivUser)


            val userItemAdapter = UserItemAdapter(user.items)
            itemView.rvUserItem.layoutManager = GridLayoutManager(itemView.context, 2)
            itemView.rvUserItem.addItemDecoration(
                DividerItemDecoration(
                    itemView.rvUserItem.context,
                    (itemView.rvUserItem.layoutManager as LinearLayoutManager).orientation
                )
            )
            itemView.rvUserItem.adapter = userItemAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_user, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position], position)

    fun addData(list: List<ApiUser.Data.User>) {
        users.addAll(list)
    }

}
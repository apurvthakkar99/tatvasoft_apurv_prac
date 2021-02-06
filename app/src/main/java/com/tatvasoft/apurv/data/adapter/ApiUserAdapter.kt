package com.tatvasoft.apurv.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tatvasoft.apurv.R
import com.tatvasoft.apurv.data.model.ApiUser
import kotlinx.android.synthetic.main.row_user.view.*

class ApiUserAdapter(
    private val users: ArrayList<ApiUser>
) : RecyclerView.Adapter<ApiUserAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: ApiUser, position: Int) {
//            itemView.textViewUserName.text = user.name
//            itemView.textViewUserEmail.text = user.email
            Glide.with(itemView.ivUser.context)
                .load(user.data.users[position].image)
                .into(itemView.ivUser)
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

    fun addData(list: List<ApiUser>) {
        users.addAll(list)
    }

}
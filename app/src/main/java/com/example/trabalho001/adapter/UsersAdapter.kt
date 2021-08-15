package com.example.trabalho001.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trabalho001.R
import com.example.trabalho001.interfaces.ClickableItem
import com.example.trabalho001.model.User

class UsersAdapter(
    var usersList: MutableList<User>,
    val onClickable: ClickableItem
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_users_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        usersList[position].apply {
            holder.bind(this)

            holder.itemView.findViewById<Button>(R.id.infoButton).setOnClickListener {
                onClickable.onClickInfo(this)
            }
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun update(newList: List<User>) {
        usersList = mutableListOf()
        usersList.addAll(newList)
        notifyDataSetChanged()
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(user: User) {
        itemView.findViewById<ImageView>(R.id.userAvatarImageView).apply {
            Glide.with(context).load(user.avatarURL).placeholder(R.drawable.ic_github).into(this)
        }
        itemView.findViewById<TextView>(R.id.userNameTextView).text = user.name
        itemView.findViewById<TextView>(R.id.userDescriptionTextView).text = user.description
    }
}
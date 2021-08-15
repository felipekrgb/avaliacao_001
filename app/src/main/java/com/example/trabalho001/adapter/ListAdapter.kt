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
import com.example.trabalho001.TypeList
import com.example.trabalho001.interfaces.ClickableItem
import com.example.trabalho001.model.StarredRepository
import com.example.trabalho001.model.User
import com.example.trabalho001.singleton.UserSingleton

class ListAdapter<T>(
    var list: MutableList<T>,
    val onClickable: ClickableItem
) : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var typeOfCurrentList: TypeList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = when (typeOfCurrentList) {
            TypeList.USER -> R.layout.item_users_list
            TypeList.REPOSITORIES -> R.layout.item_repositories_list
        }

        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].apply {
            holder.bind(this)

            if (this is User) {
                holder.itemView.findViewById<Button>(R.id.infoButton).setOnClickListener {
                    onClickable.onClickInfo(this)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(newList: List<T>, typeList: TypeList) {
        typeOfCurrentList = typeList
        list = mutableListOf()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun <T> bind(item: T) {

        when (item) {
            is User -> {
                itemView.findViewById<ImageView>(R.id.userAvatarImageView).apply {
                    Glide.with(context).load(item.avatarURL).placeholder(R.drawable.ic_github)
                        .into(this)
                }
                itemView.findViewById<TextView>(R.id.userNameTextView).text = item.name
                itemView.findViewById<TextView>(R.id.userDescriptionTextView).text =
                    item.description
            }

            is StarredRepository -> {
                itemView.findViewById<ImageView>(R.id.repositoryAvatarImageView).apply {
                    Glide.with(context).load(item.owner.avatar).placeholder(R.drawable.ic_github)
                        .into(this)
                }
                itemView.findViewById<TextView>(R.id.repositoryNameTextView).text = item.name
                itemView.findViewById<TextView>(R.id.repositoryOwnerTextView).text =
                    item.owner.login
            }
        }


    }
}
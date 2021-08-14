package com.example.trabalho001.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho001.R
import com.example.trabalho001.model.Repository

class RepositoriesAdapter(
    var repositoriesList: MutableList<Repository> = mutableListOf()
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repositories_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        repositoriesList[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int {
        return repositoriesList.size
    }

    fun update(newList: List<Repository>) {
        repositoriesList = mutableListOf()
        repositoriesList.addAll(newList)
        notifyDataSetChanged()
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(repository: Repository) {
        itemView.findViewById<TextView>(R.id.itemRepositoryCardView).text = repository.name
    }
}
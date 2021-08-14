package com.example.trabalho001.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho001.R
import com.example.trabalho001.adapter.RepositoriesAdapter
import com.example.trabalho001.singleton.RepositorySingleton

class RepositoryListFragment : Fragment(R.layout.repos_list_fragment) {

    private lateinit var recyclerViewList: RecyclerView
    private var adapter : RepositoriesAdapter = RepositoriesAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter.update(RepositorySingleton.repositoryList)
    }

    companion object {
        fun newInstance() = RepositoryListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewList = view.findViewById<RecyclerView>(R.id.repositoriesListRecyclerView)
        recyclerViewList.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewList.adapter = adapter
    }
}
package com.example.trabalho001.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho001.R
import com.example.trabalho001.adapter.UsersAdapter
import com.example.trabalho001.singleton.UserSingleton

class UsersListFragment : Fragment(R.layout.users_list_fragment) {

    private lateinit var recyclerViewList: RecyclerView
    private var adapter : UsersAdapter = UsersAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter.update(UserSingleton.userList)
    }

    companion object {
        fun newInstance() = UsersListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewList = view.findViewById(R.id.usersListRecyclerView)
        recyclerViewList.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewList.adapter = adapter
    }
}
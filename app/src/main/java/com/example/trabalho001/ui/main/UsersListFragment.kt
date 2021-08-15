package com.example.trabalho001.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho001.R
import com.example.trabalho001.UserInfoActivity
import com.example.trabalho001.adapter.UsersAdapter
import com.example.trabalho001.interfaces.ClickableItem
import com.example.trabalho001.model.User
import com.example.trabalho001.singleton.UserSingleton

class UsersListFragment : Fragment(R.layout.users_list_fragment), ClickableItem {

    private lateinit var recyclerViewList: RecyclerView
    private var adapter: UsersAdapter = UsersAdapter(mutableListOf(), this)

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

        if (UserSingleton.userList.isEmpty()) {
            recyclerViewList.visibility = View.GONE
            view.findViewById<TextView>(R.id.noUserFoundTextView).visibility = View.VISIBLE
        } else {
            recyclerViewList.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewList.adapter = adapter
        }
    }

    override fun onClickInfo(user: User) {
        val intentToUserInfo = Intent(activity?.applicationContext, UserInfoActivity::class.java)
        intentToUserInfo.putExtra("user_parameter", user)
        startActivity(intentToUserInfo)
    }
}
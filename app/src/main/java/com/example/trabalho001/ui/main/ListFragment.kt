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
import com.example.trabalho001.TypeList
import com.example.trabalho001.ui.activity.UserInfoActivity
import com.example.trabalho001.adapter.ListAdapter
import com.example.trabalho001.interfaces.ClickableItem
import com.example.trabalho001.model.ParameterFrag
import com.example.trabalho001.model.User

class ListFragment<T> : Fragment(R.layout.list_fragment), ClickableItem {

    private lateinit var recyclerViewList: RecyclerView
    private var adapter = ListAdapter<T>(mutableListOf<T>(), this)
    private lateinit var data: ParameterFrag<T>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        arguments?.getSerializable("parameter-list")?.let {
            data = it as ParameterFrag<T>
            adapter.update(data.list, data.typeList)
        }
    }

    companion object {
        fun <T> newInstance(list: MutableList<T>, typeList: TypeList): ListFragment<T> {
            return ListFragment<T>().apply {
                arguments = Bundle().apply {
                    putSerializable("parameter-list", ParameterFrag(list, typeList))
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewList = view.findViewById(R.id.listRecyclerView)

        if (data.list.isEmpty()) {
            recyclerViewList.visibility = View.GONE
            val errorTextView = view.findViewById<TextView>(R.id.errorListTextView)

            when (data.typeList) {
                TypeList.USER -> {
                    errorTextView.text = getString(R.string.no_user_error)
                }

                TypeList.REPOSITORIES -> {
                    errorTextView.text = getString(R.string.no_repositories_error)
                }
            }

            errorTextView.visibility = View.VISIBLE


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
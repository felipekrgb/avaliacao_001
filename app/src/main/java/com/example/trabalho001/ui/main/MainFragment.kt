package com.example.trabalho001.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.trabalho001.R
import com.example.trabalho001.model.Repository
import com.example.trabalho001.singleton.RepositorySingleton

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var inputRepos: EditText
    private lateinit var buttonAddRepos: EditText

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadComponents(view)
        loadEvents()
    }

    private fun loadEvents() {
        buttonAddRepos.setOnClickListener {
            addRepos()
            println(RepositorySingleton.repositoryList)
        }
    }

    private fun addRepos() {
        RepositorySingleton.addToRepositoryList(inputRepos.text.toString())
    }

    private fun loadComponents(view: View) {
        inputRepos = view.findViewById(R.id.reposNameEditText)
        buttonAddRepos = view.findViewById(R.id.addReposButton)
    }

}
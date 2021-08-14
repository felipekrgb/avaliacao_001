package com.example.trabalho001.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.trabalho001.R
import com.example.trabalho001.endpoint.RetrofitBuilder
import com.example.trabalho001.model.Repository
import com.example.trabalho001.singleton.RepositorySingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(R.layout.main_fragment), Callback<Repository> {

    private lateinit var inputRepos: EditText
    private lateinit var buttonAddRepos: Button

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
            callFindRepos()
        }
    }

    private fun callFindRepos() {
        val reposPath = inputRepos.text.toString()

        if (reposPath.isEmpty()) {
            println("Digite um repositório válido")
        }

        val serviceInstance = RetrofitBuilder.getServiceGithubInstance()
        val call = serviceInstance.getNewRepos(reposPath)

        call.clone().enqueue(this)
    }

    private fun addRepos(repository: Repository) {
        RepositorySingleton.addToRepositoryList(repository)
    }

    private fun loadComponents(view: View) {
        inputRepos = view.findViewById(R.id.reposNameEditText)
        buttonAddRepos = view.findViewById(R.id.addReposButton)
    }

    override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
        println("Adicionado com sucesso")
        response.body()?.apply {
            addRepos(this)
        }
    }

    override fun onFailure(call: Call<Repository>, t: Throwable) {
        println("Digite um repositório válido - FAILURE")
    }
}
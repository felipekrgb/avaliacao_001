package com.example.trabalho001.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.trabalho001.R
import com.example.trabalho001.endpoint.RetrofitBuilder
import com.example.trabalho001.model.User
import com.example.trabalho001.singleton.UserSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(R.layout.main_fragment), Callback<User> {

    private lateinit var inputUser: EditText
    private lateinit var buttonAddUser: Button

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadComponents(view)
        loadEvents()
    }

    private fun loadComponents(view: View) {
        inputUser = view.findViewById(R.id.userLoginEditText)
        buttonAddUser = view.findViewById(R.id.addUserButton)
    }

    private fun loadEvents() {
        buttonAddUser.setOnClickListener {
            callFindUser()
        }
    }

    private fun callFindUser() {
        val userLogin = inputUser.text.toString()

        if (userLogin.isEmpty()) {
            println("Digite um usuário")
        }

        val serviceInstance = RetrofitBuilder.getServiceGithubInstance()
        val call = serviceInstance.getNewUser(userLogin)

        call.clone().enqueue(this)
    }

    private fun addUser(user: User) {
        UserSingleton.addToUsersList(user)
    }

    override fun onResponse(call: Call<User>, response: Response<User>) {
        println("Adicionado com sucesso")
        response.body()?.apply {
            addUser(this)
        }
    }

    override fun onFailure(call: Call<User>, t: Throwable) {
        println("Digite um usuário válido - FAILURE")
    }
}
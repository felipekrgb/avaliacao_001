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
import com.example.trabalho001.utils.snackBar
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(R.layout.main_fragment), Callback<User> {

    private lateinit var inputUser: EditText
    private lateinit var buttonAddUser: Button
    private lateinit var viewFragment: View

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewFragment = view
        loadComponents(view)
        loadEvents()
    }

    private fun loadComponents(view: View) {
        inputUser = view.findViewById(R.id.userLoginEditText)
        buttonAddUser = view.findViewById(R.id.addUserButton)
    }

    private fun loadEvents() {
        buttonAddUser.setOnClickListener {
            buttonAddUser.isEnabled = false
            buttonAddUser.isClickable = false
            callFindUser()
        }
    }

    private fun turnButtonOn() {
        buttonAddUser.isEnabled = true
        buttonAddUser.isClickable = true
    }

    private fun callFindUser() {
        val userLogin = inputUser.text.toString()

        if (userLogin.isEmpty()) {
            turnButtonOn()
            snackBar(viewFragment, R.string.empty_user_error, R.color.snackbar_error)
        } else {
            val serviceInstance = RetrofitBuilder.getServiceGithubInstance()
            val call = serviceInstance.getNewUser(userLogin)

            call.clone().enqueue(this)
        }
    }

    private fun addUser(user: User) {
        UserSingleton.addToUsersList(user)
    }

    override fun onResponse(call: Call<User>, response: Response<User>) {
        turnButtonOn()
        if (response.body() != null) {
            snackBar(viewFragment, R.string.user_added_success, R.color.snackbar_success)
            response.body()?.apply {
                addUser(this)
            }
        } else {
            snackBar(viewFragment, R.string.user_invalid_error, R.color.snackbar_error)
        }
    }

    override fun onFailure(call: Call<User>, t: Throwable) {
        turnButtonOn()
        snackBar(viewFragment, R.string.error, R.color.snackbar_error)
    }
}
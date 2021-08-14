package com.example.trabalho001.endpoint

import com.example.trabalho001.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}")
    fun getNewUser(@Path("user") user: String) : Call<User>
}
package com.example.trabalho001.endpoint

import com.example.trabalho001.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/repos/{newRepos}")
    fun getNewRepos(@Path("newRepos") reposPath: String) : Call<Repository>
}
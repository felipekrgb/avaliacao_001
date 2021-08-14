package com.example.trabalho001.endpoint

import com.example.trabalho001.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("repos/{reposPath}")
    fun getNewRepos(@Path("reposPath", encoded = true) reposPath: String) : Call<Repository>
}
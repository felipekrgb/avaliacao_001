package com.example.trabalho001.endpoint

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val retrofit =
        Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getServiceGithubInstance() : GithubService {
        return retrofit.create(GithubService::class.java)
    }
}
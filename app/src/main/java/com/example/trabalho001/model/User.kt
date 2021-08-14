package com.example.trabalho001.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val userLogin: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("bio")
    val description: String,
    @SerializedName("avatar_url")
    val avatarURL: String,
)

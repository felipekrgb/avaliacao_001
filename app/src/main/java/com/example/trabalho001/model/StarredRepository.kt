package com.example.trabalho001.model

import com.google.gson.annotations.SerializedName

data class StarredRepository(
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("name")
    val name: String,
)

data class Owner(
    @SerializedName("avatar_url")
    val avatar: String,
    @SerializedName("login")
    val login: String,
)
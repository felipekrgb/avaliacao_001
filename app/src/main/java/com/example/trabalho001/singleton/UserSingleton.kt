package com.example.trabalho001.singleton

import com.example.trabalho001.model.User

object UserSingleton {

    var userList: MutableList<User> = mutableListOf()

    fun addToUsersList(user: User) {
        userList.add(user)
    }

}
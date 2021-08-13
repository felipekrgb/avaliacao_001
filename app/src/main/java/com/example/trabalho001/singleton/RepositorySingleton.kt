package com.example.trabalho001.singleton

import com.example.trabalho001.model.Repository

object RepositorySingleton {

    var repositoryList: MutableList<String> = mutableListOf()

    fun addToRepositoryList(reposPath: String) {
        repositoryList.add(reposPath)
    }

}
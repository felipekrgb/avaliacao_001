package com.example.trabalho001.singleton

import com.example.trabalho001.model.Repository

object RepositorySingleton {

    var repositoryList: MutableList<Repository> = mutableListOf()

    fun addToRepositoryList(repository: Repository) {
        repositoryList.add(repository)
    }

}
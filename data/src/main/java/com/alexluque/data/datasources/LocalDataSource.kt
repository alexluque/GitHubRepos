package com.alexluque.data.datasources

import com.alexluque.domain.Repo

interface LocalDataSource {

    suspend fun getRepos(): List<Repo>

    suspend fun insertRepo(repo: Repo): Long
}
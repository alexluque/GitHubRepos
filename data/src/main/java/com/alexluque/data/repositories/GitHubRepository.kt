package com.alexluque.data.repositories

import com.alexluque.data.datasources.LocalDataSource
import com.alexluque.data.datasources.RemoteDataSource
import com.alexluque.domain.Repo
import retrofit2.Retrofit

class GitHubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val retrofit: Retrofit
) {

    suspend fun getRepos(hasInternet: Boolean, page: Int): List<Repo> =
        if (hasInternet)
            remoteDataSource.getRepos(retrofit, page)
        else
            localDataSource.getRepos()

    suspend fun insertRepo(repo: Repo): Long = localDataSource.insertRepo(repo)
}
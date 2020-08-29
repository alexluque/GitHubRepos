package com.alexluque.githubrepos.model.network

import com.alexluque.data.datasources.RemoteDataSource
import com.alexluque.domain.Repo
import com.alexluque.githubrepos.model.network.services.GitHubService
import com.alexluque.githubrepos.model.toDomainRepo
import retrofit2.Retrofit
import com.alexluque.githubrepos.model.network.entities.Repo as NetworkRepo

class GitHubDataSource : RemoteDataSource {

    override suspend fun getRepos(retrofit: Retrofit, page: Int): List<Repo> =
        retrofit
            .create(GitHubService::class.java)
            .getRepos(page)
            .map(NetworkRepo::toDomainRepo)
            .sortedBy(Repo::name)

}
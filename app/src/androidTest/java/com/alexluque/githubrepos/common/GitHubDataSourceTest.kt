package com.alexluque.githubrepos.common

import com.alexluque.data.datasources.RemoteDataSource
import com.alexluque.domain.Repo
import retrofit2.Retrofit

class GitHubDataSourceTest : RemoteDataSource {

    override suspend fun getRepos(retrofit: Retrofit, page: Int): List<Repo> = listOf(
        Repo(
            1936771,
            "truth",
            "google",
            "https://avatars1.githubusercontent.com/u/1342004?v=4",
            "Fluent assertions for Java and Android",
            true,
            "https://github.com/google/truth",
            "https://api.github.com/users/google"
        )
    )
}
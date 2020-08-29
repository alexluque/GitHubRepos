package com.alexluque.githubrepos.di

import com.alexluque.data.repositories.GitHubRepository
import com.alexluque.usecases.GetRepos
import com.alexluque.usecases.InsertRepo
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun getReposProvider(
        repository: GitHubRepository
    ): GetRepos = GetRepos(repository)

    @Provides
    fun insertRepoProvider(
        repository: GitHubRepository
    ): InsertRepo = InsertRepo(repository)
}
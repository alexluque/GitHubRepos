package com.alexluque.githubrepos.ui.main

import com.alexluque.data.repositories.GitHubRepository
import com.alexluque.usecases.GetRepos
import com.alexluque.usecases.InsertRepo
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.Dispatchers

@Module
class MainActivityModule {

    @Provides
    fun mainViewModelProvider(
        getRepos: GetRepos,
        insertRepo: InsertRepo
    ): MainViewModel = MainViewModel(getRepos, insertRepo, Dispatchers.Main)

    @Provides
    fun getReposProvider(
        repository: GitHubRepository
    ): GetRepos = GetRepos(repository)

    @Provides
    fun insertRepoProvider(
        repository: GitHubRepository
    ): InsertRepo = InsertRepo(repository)

}

@Subcomponent(modules = [(MainActivityModule::class)])
interface MainActivityComponent {

    val mainViewModel: MainViewModel
}
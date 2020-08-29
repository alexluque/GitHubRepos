package com.alexluque.githubrepos.di

import com.alexluque.data.datasources.LocalDataSource
import com.alexluque.data.datasources.RemoteDataSource
import com.alexluque.data.repositories.GitHubRepository
import com.alexluque.githubrepos.model.network.RetrofitBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    fun retrofitProvider(): Retrofit = RetrofitBuilder.instance

    @Provides
    fun gitHubRepositoryProvider(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        retrofit: Retrofit
    ): GitHubRepository = GitHubRepository(remoteDataSource, localDataSource, retrofit)
}
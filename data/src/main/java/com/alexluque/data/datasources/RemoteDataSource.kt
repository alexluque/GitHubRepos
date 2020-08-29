package com.alexluque.data.datasources

import com.alexluque.domain.Repo
import retrofit2.Retrofit

interface RemoteDataSource {

    suspend fun getRepos(retrofit: Retrofit, page: Int): List<Repo>
}

package com.alexluque.githubrepos.model.network.services

import com.alexluque.githubrepos.model.network.entities.Repo
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    /**
     * We are not explicitly specifying the 'per_page' param
     * which gives us the default amount of 30 results per query.
     */
    @GET("orgs/google/repos")
    suspend fun getRepos(
        @Query("page") page: Int
    ): List<Repo>
}
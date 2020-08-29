package com.alexluque.usecases

import com.alexluque.data.repositories.GitHubRepository
import com.alexluque.domain.Repo

class GetRepos(
    private val repository: GitHubRepository
) {

    suspend fun invoke(hasInternet: Boolean, page: Int): List<Repo> = repository.getRepos(hasInternet, page)
}
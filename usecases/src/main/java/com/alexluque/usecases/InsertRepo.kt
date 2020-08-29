package com.alexluque.usecases

import com.alexluque.data.repositories.GitHubRepository
import com.alexluque.domain.Repo

class InsertRepo(
    private val repository: GitHubRepository
) {

    suspend fun invoke(repo: Repo): Long = repository.insertRepo(repo)
}
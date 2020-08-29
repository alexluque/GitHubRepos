package com.alexluque.githubrepos.model.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alexluque.githubrepos.model.database.entities.Repo

@Dao
interface RepoDao {

    @Query("SELECT * FROM repos ORDER BY name ASC")
    suspend fun getRepos(): List<Repo>

    @Insert
    suspend fun insertRepo(repo: Repo): Long
}
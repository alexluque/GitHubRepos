package com.alexluque.githubrepos.model.database

import com.alexluque.data.datasources.LocalDataSource
import com.alexluque.githubrepos.model.toDBRepo
import com.alexluque.githubrepos.model.toDomainRepo
import com.alexluque.domain.Repo as DomainRepo
import com.alexluque.githubrepos.model.database.entities.Repo as DBRepo

class RoomDataSource(db: ReposRoomDatabase) : LocalDataSource {

    private val repoDao = db.repoDao()

    override suspend fun getRepos(): List<DomainRepo> = repoDao.getRepos().map(DBRepo::toDomainRepo)

    override suspend fun insertRepo(repo: DomainRepo): Long = repoDao.insertRepo(repo.toDBRepo())
}
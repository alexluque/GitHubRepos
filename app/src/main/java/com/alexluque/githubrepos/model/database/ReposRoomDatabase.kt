package com.alexluque.githubrepos.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexluque.githubrepos.model.database.daos.RepoDao
import com.alexluque.githubrepos.model.database.entities.Repo

@Database(
    entities = [Repo::class],
    version = 2,
    exportSchema = false
)
abstract class ReposRoomDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao
}
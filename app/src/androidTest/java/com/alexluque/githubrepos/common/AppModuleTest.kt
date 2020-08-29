package com.alexluque.githubrepos.common

import android.app.Application
import androidx.room.Room
import com.alexluque.data.datasources.LocalDataSource
import com.alexluque.data.datasources.RemoteDataSource
import com.alexluque.githubrepos.model.database.ReposRoomDatabase
import com.alexluque.githubrepos.model.database.RoomDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModuleTest {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        ReposRoomDatabase::class.java,
        "github_repos_database"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun remoteDataSourceProvider(): RemoteDataSource = GitHubDataSourceTest()

    @Provides
    @Singleton
    fun localDataSourceProvider(
        reposRoomDatabase: ReposRoomDatabase
    ): LocalDataSource = RoomDataSource(reposRoomDatabase)
}
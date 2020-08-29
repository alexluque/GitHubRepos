package com.alexluque.githubrepos.common

import android.app.Application
import com.alexluque.data.datasources.LocalDataSource
import com.alexluque.data.datasources.RemoteDataSource
import com.alexluque.githubrepos.di.DataModule
import com.alexluque.githubrepos.di.GitHubReposComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModuleTest::class, DataModule::class])
interface UiTestComponent : GitHubReposComponent {

    val localDataSource: LocalDataSource
    val remoteDataSource: RemoteDataSource

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): UiTestComponent
    }
}
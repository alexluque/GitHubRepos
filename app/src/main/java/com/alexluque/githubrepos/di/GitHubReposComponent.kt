package com.alexluque.githubrepos.di

import android.app.Application
import com.alexluque.githubrepos.ui.main.MainActivityComponent
import com.alexluque.githubrepos.ui.main.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface GitHubReposComponent {

    fun plus(mainModule: MainActivityModule): MainActivityComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): GitHubReposComponent
    }
}
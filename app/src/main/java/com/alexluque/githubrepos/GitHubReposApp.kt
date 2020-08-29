package com.alexluque.githubrepos

import androidx.multidex.MultiDexApplication
import com.alexluque.githubrepos.di.DaggerGitHubReposComponent
import com.alexluque.githubrepos.di.GitHubReposComponent

open class GitHubReposApp : MultiDexApplication() {

    lateinit var component: GitHubReposComponent
        private set

    override fun onCreate() {
        super.onCreate()
        component = initGitHubReposAppComponent()
    }

    open fun initGitHubReposAppComponent(): GitHubReposComponent = DaggerGitHubReposComponent.factory().create(this)
}
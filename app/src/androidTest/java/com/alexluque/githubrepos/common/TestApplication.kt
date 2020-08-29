package com.alexluque.githubrepos.common

import com.alexluque.githubrepos.GitHubReposApp
import com.alexluque.githubrepos.di.GitHubReposComponent

class TestApplication : GitHubReposApp() {

    override fun initGitHubReposAppComponent(): GitHubReposComponent =
        DaggerUiTestComponent.factory().create(this)
}
package com.alexluque.githubrepos.integration

import com.alexluque.githubrepos.model.network.GitHubDataSource
import com.alexluque.githubrepos.model.network.RetrofitBuilder
import com.alexluque.githubrepos.model.network.entities.Owner
import com.alexluque.githubrepos.model.network.services.GitHubService
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import com.alexluque.domain.Repo as DomainRepo
import com.alexluque.githubrepos.model.network.entities.Repo as NetworkRepo

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GitHubDataSourceTest {

    private val gitHubServiceMock = Mockito.mock(GitHubService::class.java)

    @Spy
    private lateinit var dataSourceMock: GitHubDataSource

    @Spy
    private val retrofitStub = RetrofitBuilder.instance

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `Domain repos ordered by name returned when network repos retrieved from API`() {
        runBlockingTest {
            val page = 1
            val owner = Owner("owner", "avatar url", "owner url")
            val isFork = true
            val repoName1 = "repo 1"
            val repoName2 = "repo 2"
            val repoUrl = "repo url"
            val repoDescription = "description"
            val fakeAPIData = listOf(
                NetworkRepo(2, repoName2, owner, repoUrl, repoDescription, isFork),
                NetworkRepo(1, repoName1, owner, repoUrl, repoDescription, isFork)
            )

            whenever(retrofitStub.create(GitHubService::class.java)).thenReturn(gitHubServiceMock)
            whenever(gitHubServiceMock.getRepos(page)).thenReturn(fakeAPIData)

            val expect = listOf(
                DomainRepo(1, repoName1, owner.login, owner.avatar_url, repoDescription, isFork, repoUrl, owner.html_url),
                DomainRepo(2, repoName2, owner.login, owner.avatar_url, repoDescription, isFork, repoUrl, owner.html_url)
            )
            val result = dataSourceMock.getRepos(retrofitStub, page)

            Assert.assertEquals(expect, result)
        }
    }
}
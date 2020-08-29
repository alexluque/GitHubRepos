package com.alexluque.githubrepos.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.alexluque.domain.Repo
import com.alexluque.usecases.GetRepos
import com.alexluque.usecases.InsertRepo
import com.alexluque.githubrepos.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getReposMock: GetRepos

    @Mock
    private lateinit var insertRepoMock: InsertRepo

    @Mock
    private lateinit var loadingObserverMock: Observer<Boolean>

    @Mock
    private lateinit var reposObserverMock: Observer<List<Repo>>

    private lateinit var viewModel: MainViewModel

    private val fakeRepos = listOf(Repo(1, "repo 1", "owner login", "owner avatar url", "description", true, "repo url", "owner url"))

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(getReposMock, insertRepoMock, Dispatchers.Unconfined)
    }

    @Test
    fun `Loading is false initially, turns true when loadRepos`() {
        viewModel.loading.observeForever(loadingObserverMock)

        viewModel.loadData(true)

        verify(loadingObserverMock).onChanged(true)
    }

    @Test
    fun `Repo list is populated with network data`() {
        runBlockingTest {
            whenever(getReposMock.invoke(true, 1)).thenReturn(fakeRepos)
            whenever(getReposMock.invoke(false, 1)).thenReturn(fakeRepos)

            viewModel.repos.observeForever(reposObserverMock)

            viewModel.loadData(true)

            verify(reposObserverMock).onChanged(fakeRepos)
        }
    }

    @Test
    fun `Repo list is populated with local data`() {
        runBlockingTest {
            whenever(getReposMock.invoke(false, 1)).thenReturn(fakeRepos)

            viewModel.repos.observeForever(reposObserverMock)

            viewModel.loadData(false)

            verify(reposObserverMock).onChanged(fakeRepos)
        }
    }

    @Test
    fun `New repos are inserted accordingly`() {
        runBlockingTest {
            whenever(getReposMock.invoke(true, 1)).thenReturn(fakeRepos)
            whenever(getReposMock.invoke(false, 1)).thenReturn(fakeRepos)
            viewModel.repos.observeForever(reposObserverMock)
            viewModel.loadData(true)
            verify(reposObserverMock).onChanged(fakeRepos)

            val newRepos = listOf(Repo(2, "repo 2", "owner login", "owner avatar url", "description 2", true, "repo 2 url", "owner url"))
            viewModel.insertNewRepos(newRepos)
            val allRepos = fakeRepos + newRepos
            whenever(getReposMock.invoke(true, 1)).thenReturn(allRepos)
            viewModel.loadData(true)
            verify(reposObserverMock).onChanged(allRepos)
        }
    }

    @Test
    fun `Local data loaded when no Internet connection`() {
        runBlockingTest {
            viewModel.loadData(false)
            verify(getReposMock, times(1)).invoke(false, 1)
        }
    }

    @Test
    fun `Remote data loaded when there is Internet connection`() {
        runBlockingTest {
            viewModel.loadData(true)
            verify(getReposMock, times(1)).invoke(true, 1)
        }
    }
}
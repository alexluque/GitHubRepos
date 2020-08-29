package com.alexluque.githubrepos.unit

import com.alexluque.domain.Repo
import com.alexluque.githubrepos.model.database.RoomDataSource
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RoomDataSourceTest {

    @Mock
    private lateinit var dsMock: RoomDataSource

    private val fakeRepo = Repo(1, "repo 1", "owner login", "owner avatar", "description", true, "repo url", "owner url")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `Get repos`() {
        runBlockingTest {
            val expect = listOf(fakeRepo)

            whenever(dsMock.getRepos()).thenReturn(expect)

            val result = dsMock.getRepos()

            Assert.assertEquals(expect, result)
        }
    }

    @Test
    fun `Insert repo`() {
        runBlockingTest {
            val expect = fakeRepo.id

            whenever(dsMock.insertRepo(fakeRepo)).thenReturn(fakeRepo.id)

            val result = dsMock.insertRepo(fakeRepo)

            Assert.assertEquals(expect, result)
        }
    }
}
package com.alexluque.githubrepos.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexluque.domain.Repo
import com.alexluque.usecases.GetRepos
import com.alexluque.usecases.InsertRepo
import com.alexluque.githubrepos.ui.common.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch


class MainViewModel(
    private val getRepos: GetRepos,
    private val insertRepo: InsertRepo,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _repos = MutableLiveData<List<Repo>>()
    val repos: LiveData<List<Repo>> get() = _repos

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    init {
        initScope()
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

    fun loadData(hasInternet: Boolean) = launch {
        _loading.value = true

        _repos.value = getRepos.invoke(hasInternet, 1)

        if (hasInternet)
            _repos.value?.let { insertNewRepos(it) }

        _loading.value = false
    }

    fun insertNewRepos(repos: List<Repo>) = launch {
        val localIds = getRepos.invoke(false, 1).map(Repo::id)

        repos.forEach {
            if (!localIds.contains(it.id))
                insertRepo.invoke(it)
        }
    }
}
package com.alexluque.githubrepos.ui.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.alexluque.githubrepos.ui.common.Scope
import kotlinx.coroutines.CoroutineDispatcher

abstract class ScopedViewModel(uiDispatcher: CoroutineDispatcher) : ViewModel(),
    Scope by Scope.Impl(uiDispatcher) {

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}
@file:Suppress("DEPRECATION")

package com.alexluque.githubrepos.ui.common.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.alexluque.githubrepos.GitHubReposApp
import kotlin.properties.Delegates

val Context.app: GitHubReposApp
    get() = applicationContext as GitHubReposApp

/**
 * This method is using deprecated functionalities
 * due to the need of use min API 15.
 */
fun Context.hasInternet(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}
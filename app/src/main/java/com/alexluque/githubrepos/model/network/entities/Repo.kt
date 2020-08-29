package com.alexluque.githubrepos.model.network.entities

data class Repo(
    val id: Long,
    val name: String,
    val owner: Owner,
    val html_url: String,
    val description: String,
    val fork: Boolean
)
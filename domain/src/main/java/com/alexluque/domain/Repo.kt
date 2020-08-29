package com.alexluque.domain

data class Repo(
    val id: Long,
    val name: String,
    val ownerLogin: String,
    val ownerAvatarUrl: String,
    val description: String? = "",
    val fork: Boolean,
    val repoUrl: String,
    val ownerUrl: String
)
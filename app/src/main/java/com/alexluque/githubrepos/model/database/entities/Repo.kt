package com.alexluque.githubrepos.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
class Repo(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "owner_login") val ownerLogin: String,
    @ColumnInfo(name = "owner_avatar_url") val ownerAvatarUrl: String,
    @ColumnInfo(name = "description") val description: String? = "",
    @ColumnInfo(name = "forked") val fork: Boolean,
    @ColumnInfo(name = "repo_url") val repoUrl: String,
    @ColumnInfo(name = "owner_url") val ownerUrl: String
)
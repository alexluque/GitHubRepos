package com.alexluque.githubrepos.model

import com.alexluque.domain.Repo as DomainRepo
import com.alexluque.githubrepos.model.database.entities.Repo as DBRepo
import com.alexluque.githubrepos.model.network.entities.Repo as NetworkRepo

fun DBRepo.toDomainRepo(): DomainRepo = DomainRepo(id, name, ownerLogin, ownerAvatarUrl, description, fork, repoUrl, ownerUrl)

fun NetworkRepo.toDomainRepo(): DomainRepo =
    DomainRepo(id, name, ownerLogin = owner.login, ownerAvatarUrl = owner.avatar_url, description = description, fork = fork, repoUrl = html_url, ownerUrl = owner.html_url)

fun DomainRepo.toDBRepo(): DBRepo = DBRepo(id, name, ownerLogin, ownerAvatarUrl, description, fork, repoUrl, ownerUrl)
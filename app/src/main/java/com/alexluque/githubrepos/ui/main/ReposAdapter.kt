package com.alexluque.githubrepos.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alexluque.domain.Repo
import com.alexluque.githubrepos.R
import com.alexluque.githubrepos.ui.common.extensions.loadImage
import kotlinx.android.synthetic.main.repo_card.view.*

class ReposAdapter(
    var repos: MutableList<Repo>,
    private val navigate: (String, String) -> Unit
) : RecyclerView.Adapter<ReposAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_card, parent, false)
        return MyViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val context = holder.context
        val view = holder.view
        val repo = repos[position]
        view.owner_avatar.loadImage(repo.ownerAvatarUrl)
        view.repo_name.text = repo.name
        view.owner_login.text = repo.ownerLogin
        view.repo_description.text = repo.description
        view.setBackgroundColor(
            if (repo.fork)
                ContextCompat.getColor(context, R.color.colorLightGreen)
            else
                ContextCompat.getColor(context, R.color.colorWhite)
        )
        view.setOnLongClickListener {
            navigate(repo.repoUrl, repo.ownerUrl)
            true
        }
    }

}
package com.alexluque.githubrepos.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexluque.domain.Repo
import com.alexluque.githubrepos.R
import com.alexluque.githubrepos.databinding.ActivityMainBinding
import com.alexluque.githubrepos.ui.common.extensions.*

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ReposAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var component: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        component = app.component.plus(MainActivityModule())
        viewModel = getViewModel { component.mainViewModel }
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        setAdapter()

        viewModel.repos.observe(this, Observer(::observeContent))

        loadData()
    }

    private fun setAdapter() {
        viewAdapter = ReposAdapter(mutableListOf<Repo>(), ::navigate)
        recyclerView = binding.reposRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }
    }

    private fun loadData() {
        val hasInternet = this.hasInternet()
        viewModel.loadData(hasInternet)

        if (!hasInternet)
            this.binding.root.makeLongSnackbar(getString(R.string.loading_local_data))
    }

    private fun observeContent(repos: List<Repo>) =
        viewAdapter.updateData(viewAdapter.repos as MutableList<Any>, repos)

    private fun navigate(repoUrl: String, ownerUrl: String) =
        NavigationFragment(repoUrl, ownerUrl).show(supportFragmentManager, "NavigationFragment")
}

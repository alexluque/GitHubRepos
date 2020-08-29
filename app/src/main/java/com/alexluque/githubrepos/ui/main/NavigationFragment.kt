package com.alexluque.githubrepos.ui.main

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.alexluque.githubrepos.R

class NavigationFragment(
    private val repoUrl: String,
    private val ownerUrl: String
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.open_page))
                .setItems(R.array.navigation_options,
                    DialogInterface.OnClickListener { _, which ->
                        navigate(which)
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun navigate(urlOption: Int) {
        val url = if (urlOption == 0) repoUrl else ownerUrl
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.startActivity(browserIntent)
    }
}
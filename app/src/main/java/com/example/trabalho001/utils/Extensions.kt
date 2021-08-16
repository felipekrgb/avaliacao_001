package com.example.trabalho001.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import com.example.trabalho001.R
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar


fun AppCompatActivity.snackBar(view: View, @StringRes msgId: Int, @ColorRes colorId: Int) {
    hideKeyboard()

    setupSnackbar(view, msgId, colorId).apply {
        this.show()
    }
}

fun AppCompatActivity.hideKeyboard() {
    val imm = window.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}

private fun AppCompatActivity.setupSnackbar(
    v: View,
    @StringRes msgId: Int,
    @ColorRes colorId: Int,
): Snackbar {
    return Snackbar.make(v, msgId, Snackbar.LENGTH_LONG).apply {
        view.setBackgroundColor(getColor(colorId))

        anchorView = v.findViewById<NavigationBarView>(R.id.bottomNav)

        view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).apply {
            setTextColor(getColor(R.color.white))
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }

        show()

    }
}
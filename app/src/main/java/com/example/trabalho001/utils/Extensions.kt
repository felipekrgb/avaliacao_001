package com.example.trabalho001.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.trabalho001.R
import com.google.android.material.snackbar.Snackbar


fun Fragment.snackBar(view: View, @StringRes msgId: Int, @ColorRes colorId: Int) {
    hideKeyboard()

    setupSnackbar(view, msgId, colorId).apply {
        this.show()
    }
}

fun Fragment.hideKeyboard() {
    val imm = requireActivity().window.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(requireActivity().window.decorView.windowToken, 0)
}

private fun Fragment.setupSnackbar(
    v: View,
    @StringRes msgId: Int,
    @ColorRes colorId: Int
): Snackbar {
    return Snackbar.make(v, msgId, Snackbar.LENGTH_LONG).apply {
        view.setBackgroundColor(requireActivity().getColor(colorId))

        view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).apply {
            setTextColor(requireActivity().getColor(R.color.white))
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
    }
}
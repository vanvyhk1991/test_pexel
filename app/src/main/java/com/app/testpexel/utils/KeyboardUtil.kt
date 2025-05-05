package com.app.testpexel.utils

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager


fun hideSoftKeyboard(context: Activity) {
    val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(context.currentFocus?.windowToken, 0)
}

fun showSoftKeyboard(context: Activity) {
    val inputMethodManager = context.getSystemService(
        INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.showSoftInput(context.currentFocus, InputMethodManager.SHOW_IMPLICIT)
}

fun hideKeyboardInAndroidFragment(view: View) {
    val imm = view.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

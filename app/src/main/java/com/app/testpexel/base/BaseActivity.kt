package com.app.testpexel.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(@LayoutRes layoutRes: Int) :
    AppCompatActivity(layoutRes) {
    protected abstract val binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        setupViews()
        bindViewModel()
    }

    override fun onPause() {
        super.onPause()
        enableAppSecurity()
    }

    override fun onResume() {
        super.onResume()
        disableAppSecurity()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        toggleAppSecurity(hasFocus)
    }

    private fun toggleAppSecurity(hasFocus: Boolean) {
        if (hasFocus) {
            disableAppSecurity()
        } else {
            enableAppSecurity()
        }
    }

    private fun enableAppSecurity() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
    }

    private fun disableAppSecurity() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

    @MainThread
    protected abstract fun setupViews()

    @MainThread
    protected abstract fun bindViewModel()

    private fun Activity.makeStatusBarTransparent(visibility: Int) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or FLAG_LAYOUT_NO_LIMITS or View.SYSTEM_UI_FLAG_VISIBLE
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            statusBarColor = ContextCompat.getColor(this@BaseActivity, R.color.color_1B1B1B)
        }
    }
}
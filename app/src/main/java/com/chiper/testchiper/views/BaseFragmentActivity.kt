package com.chiper.testchiper.views

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import com.chiper.testchiper.Navigation

open class BaseFragmentActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Navigation.setCurrentActivity(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Navigation.setCurrentActivity(this)
    }

    override fun onResume() {
        super.onResume()
        Navigation.setCurrentActivity(this)
    }

    override fun onBackPressed() {
        Navigation.onBack()
    }
}
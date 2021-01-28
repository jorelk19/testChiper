package com.chiper.testchiper.views

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.chiper.testchiper.Navigation
import com.chiper.testchiper.R
import com.chiper.testchiper.databinding.ActivityMainBinding
import com.chiper.testchiper.views.movies.MoviesFragment

class MainActivity : BaseFragmentActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        initControls()
    }

    private fun initControls() {
        Navigation.attachFragment(MoviesFragment(), R.id.fragment_container)
    }
}
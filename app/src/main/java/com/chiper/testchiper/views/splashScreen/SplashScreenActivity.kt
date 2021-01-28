package com.chiper.testchiper.views.splashScreen

import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.chiper.testchiper.Navigation
import com.chiper.testchiper.R
import com.chiper.testchiper.databinding.ActivitySplashScreenBinding
import com.chiper.testchiper.views.BaseFragmentActivity
import com.chiper.testchiper.views.MainActivity


class SplashScreenActivity : BaseFragmentActivity() {

    private lateinit var activitySplashScreenBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashScreenBinding = DataBindingUtil.setContentView(this@SplashScreenActivity, R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable {
            Navigation.goTo(MainActivity::class.java)
            this.finish()
        }, 2000)
    }
}
package com.abora.perfectobase.ui.splash

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.abora.perfectobase.R
import com.abora.perfectobase.base.BaseActivity
import com.abora.perfectobase.databinding.ActivitySplashBinding
import com.abora.perfectobase.ui.main.MainActivity
import com.abora.perfectobase.ui.splash.SplashViewModel
import com.abora.perfectobase.utils.MyUtils.makeStatusBarTransparent
import com.abora.perfectobase.utils.MyUtils.openActivity
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_splash.*
import kotlin.reflect.KClass

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    lateinit var slideDown: ObjectAnimator


    override fun resourceId(): Int = R.layout.activity_splash

    override fun viewModelClass(): KClass<SplashViewModel> = SplashViewModel::class

    override fun setUI(savedInstanceState: Bundle?) {

        slideDown = ObjectAnimator.ofFloat(ivFootBall, "translationY", -200f, 0f)
        slideDown.duration = 2000
        slideDown.start()

        Handler(Looper.getMainLooper()).postDelayed({
            openActivity(MainActivity::class.java)
        }, 1500)
    }

    override fun observer() {

    }

    override fun clicks() {

    }

    override fun callApis() {

    }
}
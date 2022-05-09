package com.abora.perfectobase.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.abora.perfectobase.R
import com.abora.perfectobase.base.BaseActivity
import com.abora.perfectobase.databinding.ActivitySplashBinding
import com.abora.perfectobase.ui.splash.SplashViewModel
import com.abora.perfectobase.utils.MyUtils.openActivity
import com.google.firebase.messaging.FirebaseMessaging
import kotlin.reflect.KClass

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {


    override fun resourceId(): Int = R.layout.activity_splash

    override fun viewModelClass(): KClass<SplashViewModel> = SplashViewModel::class

    override fun setUI(savedInstanceState: Bundle?) {
        FirebaseMessaging.getInstance().token.addOnSuccessListener { task ->
            if(task != null){
                sharedPreferences.edit().putString("pushToken", task).apply()
                Log.d("pushToken", task)
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            openActivity(SplashActivity::class.java)
        }, 1000)
    }

    override fun observer() {

    }

    override fun clicks() {

    }

    override fun callApis() {

    }
}
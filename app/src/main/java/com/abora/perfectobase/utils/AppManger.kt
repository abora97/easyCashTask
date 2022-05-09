package com.abora.perfectobase.utils

import android.content.SharedPreferences

class AppManger constructor(var sharedPreferences: SharedPreferences) {

    fun isLogin():Boolean{
        return sharedPreferences.contains("token")
    }

    fun isVendorLogin(): Boolean {
        return sharedPreferences.getString("type", "").equals("vendor")
    }

    fun logout(){
       sharedPreferences.edit().remove("user").apply()
       sharedPreferences.edit().remove("token").apply()
       sharedPreferences.edit().remove("id").apply()
       sharedPreferences.edit().remove("name").apply()
       sharedPreferences.edit().remove("type").apply()
       sharedPreferences.edit().remove("provider").apply()
    }
}
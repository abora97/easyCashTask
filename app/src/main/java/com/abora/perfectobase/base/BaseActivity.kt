package com.abora.perfectobase.base

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.abora.perfectobase.data.models.DefaultDataModel

import com.abora.perfectobase.data.remote.networkHandling.NetworkStatus
import com.abora.perfectobase.R
import com.abora.perfectobase.app.Application
import com.abora.perfectobase.databinding.DialogConfirmBinding
import com.abora.perfectobase.utils.AppManger
import com.abora.perfectobase.utils.MyUtils.myToast
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel
import retrofit2.Response
import java.util.*
import kotlin.reflect.KClass


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(),
    NetworkStatus {

    lateinit var dataBinding: T

    val viewModel: V by lazy { getViewModel(viewModelClass()) }

    val sharedPreferences: SharedPreferences by inject()

    var dialog: AlertDialog? = null

    val appManger: AppManger by inject()

    abstract fun resourceId(): Int

    abstract fun viewModelClass(): KClass<V>

//    var user: LoginDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setAppMode()
        changeLang()
        super.onCreate(savedInstanceState)
        init()

        baseObserver()

        setUI(savedInstanceState)
        clicks()
        callApis()
        observer()

    }

    private fun init(){

        viewModel.networkStatus = this
        dataBinding = DataBindingUtil.setContentView(this, resourceId())
        dataBinding.lifecycleOwner = this

//        user = Gson().fromJson(sharedPreferences.getString("user", ""), LoginDataModel::class.java)

    }

    private fun baseObserver() {

        viewModel.loading.observe(this, Observer {
            toggleLoadingDialog(it)
        })

        viewModel.showMassage.observe(this, Observer {
            myToast(it)
        })

    }


    abstract fun setUI(savedInstanceState: Bundle?)
    abstract fun observer()
    abstract fun clicks()
    abstract fun callApis()

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_change, R.anim.slide_down)

    }

    fun changeLang() {
        val res: Resources = resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        val locale = Locale(Application.language)
        if (conf.locale != locale) {
            changeLocale(this, Application.language)
            conf.setLocale(Locale(Application.language))
            res.updateConfiguration(conf, dm)
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            applicationContext.resources
                .updateConfiguration(config, applicationContext.resources.displayMetrics)
        }

    }

    fun updateCartCounter(number: Int) {
        if (sharedPreferences.contains("cartCounter")) {
            var num = sharedPreferences.getInt("cartCounter", 0) + number
            sharedPreferences.edit().putInt("cartCounter", num).apply()
        } else {
            sharedPreferences.edit().putInt("cartCounter", number).apply()
        }
    }

    fun changeLocale(context: Context, locale: String?) {
        val res: Resources = context.resources
        val conf: Configuration = res.configuration
        conf.locale = Locale(locale)
        res.updateConfiguration(conf, res.displayMetrics)

    }

    private fun setAppMode() {
//        if (sharedPreferences.contains("darkMode") && sharedPreferences.getBoolean("darkMode", false)) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        } else {
//        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onNoInternet() {

    }

    override fun onNotVerifyRequest(exception: String?) {

    }

    override fun onApiNotFound() {

    }

    override fun onNotAllowed() {

    }

    override fun onNotAuthorized(exception: String?) {
        var dialog: android.app.AlertDialog? = null
        val view = DialogConfirmBinding.inflate(LayoutInflater.from(this))
        view.txtMassage.text = resources.getString(R.string.please_login_first)
        view.btnOk.setOnClickListener {
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
//            openActivity(LoginActivity::class.java)
        }
        view.btnCancel.setOnClickListener {
            dialog?.dismiss()
        }
        dialog = android.app.AlertDialog.Builder(this)
            .setView(view.root)
            .setCancelable(false)
            .show()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    override fun onServerSideError(exception: String?) {
        var data = Gson().fromJson(exception, DefaultDataModel::class.java)
        myToast(data.message)
    }

    override fun onBadRequest(exception: String?) {
        Log.d("WTF onBadRequest", exception.toString())
        var data = Gson().fromJson(exception, DefaultDataModel::class.java)
        myToast(data.message)
    }


    override fun <T> onDynamicCode(response: Response<T>) {
        Log.d("onDynamicCode", response.toString())
    }

    override fun onConnectionFail(exception: String?) {
        Log.e("onConnectionFail",exception.toString())
        myToast("connection Fail")
    }

    fun toggleLoadingDialog(show: Boolean) {

        if (dialog == null) {
            dialog = AlertDialog.Builder(this)
                .setView(R.layout.progress)
                .setCancelable(false)
                .create()

            dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }

        if (!show)
            dialog?.dismiss()
        else if (show)
            dialog?.show()


    }

    override fun onPause() {
        super.onPause()
        dialog?.dismiss()
    }

    override fun onStop() {
        super.onStop()
        dialog?.dismiss()
    }

    fun checkWriteToStoragePermission(): Boolean {
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        val res: Int = checkCallingOrSelfPermission(permission)
        return res == PackageManager.PERMISSION_GRANTED
    }





}
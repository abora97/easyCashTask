package com.abora.perfectobase.di.module

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.provider.Settings
import com.abora.perfectobase.data.remote.RetrofitApi
import com.abora.perfectobase.app.Application
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit


const val BASE_URL = "http://api.football-data.org/v2/"
const val apiAuthToken = "2087e037d5d1424e9a298becd55924e2"


val networkModule = module {
    single { getLoggingInterceptor() }
    single { getOkHttp(get(), get(), get()) }
    single { getRetrofit(get()) }
    single { getRetrofitApi(get()) }

}

fun getLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    return httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
}

fun getOkHttp(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    context: Context,
    sharedPreferences: SharedPreferences
): OkHttpClient {

    val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
    val myCache = Cache(context.cacheDir, cacheSize)


    return OkHttpClient().newBuilder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(offlineInterceptor)
        .addNetworkInterceptor(onlineInterceptor)
        .cache(myCache)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()
                .addHeader("Accept-Language", Application.language)
                .addHeader("X-Auth-Token", apiAuthToken)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Device-Type", "android")
                .addHeader("Device-Name", android.os.Build.MODEL)

                .addHeader("Device-OS-Version", android.os.Build.VERSION.RELEASE)
                .addHeader(
                    "Device-UDID",
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                )
                .addHeader(
                    "Device-Push-Token",
                    sharedPreferences.getString("pushToken", "Not Allowed").toString()
                )
                .addHeader("mobile_version", android.os.Build.VERSION.SDK_INT.toString())

            try {
                val pInfo: PackageInfo =
                    context.packageManager.getPackageInfo(context.packageName, 0)
                val version = pInfo.versionName
                builder.addHeader("App-Version", version)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                builder.addHeader("App-Version", "1")
            }

            if (sharedPreferences.contains("token")) {
                builder.addHeader(
                    "Authorization",
                    "Bearer ${sharedPreferences.getString("token", "")}"
                )
            }
            val response = chain.proceed(builder.build())



            response
        }
        .build()
}


fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val gson = GsonBuilder()
        .registerTypeAdapter(HttpUrl::class.java, UrlDeserializer())
        .create()
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun getRetrofitApi(retrofit: Retrofit): RetrofitApi {
    return retrofit.create(RetrofitApi::class.java)
}

class UrlDeserializer : JsonDeserializer<HttpUrl> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): HttpUrl =
        json.asString.toHttpUrl()

}

var onlineInterceptor = Interceptor { chain ->
    val response = chain.proceed(chain.request())
    val maxAge = 60 // read from cache for 60 seconds even if there is internet connection
    response.newBuilder()
        .header("Cache-Control", "public, max-age=$maxAge")
        .removeHeader("Pragma")
        .build()
}

var offlineInterceptor = Interceptor { chain ->
    var request: Request = chain.request()
    if (!isInternetAvailable(Application().getAppContext()!!)) {
        val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
        request = request.newBuilder()
            .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
            .removeHeader("Pragma")
            .build()
    }
    chain.proceed(request)
}


fun isInternetAvailable(context: Context): Boolean {
    var isConnected: Boolean = false // Initial Value
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

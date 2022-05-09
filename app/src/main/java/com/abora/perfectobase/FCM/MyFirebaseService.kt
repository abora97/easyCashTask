package com.abora.perfectobase.FCM

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.abora.perfectobase.ui.splash.SplashActivity
import com.abora.perfectobase.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MyFirebaseService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.v("onMessageReceived", remoteMessage.data.toString() )
        try {
            val notifyIntent: Intent
            var extraData =
                Gson().fromJson(remoteMessage.data["extraPayload"], ExtraPayLoad::class.java)
            if (remoteMessage.data.containsKey("extraPayload")) {

                when (extraData.actionType) {
                    "general" -> {
                        notifyIntent = Intent(this, SplashActivity::class.java)
                        notifyIntent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }
                    "order" -> {
                        notifyIntent = Intent(this, SplashActivity::class.java)
                        notifyIntent.putExtra("id",extraData.actionId.toInt())
                        Log.v("tokenFromService", ""+extraData.actionId.toInt())
                        notifyIntent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }
                    else -> {
                        notifyIntent = Intent(this, SplashActivity::class.java)
                        notifyIntent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }
                }
            }
            else {
                notifyIntent = Intent(this, SplashActivity::class.java)
                notifyIntent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

            if (!extraData.image.isNullOrEmpty()) {
                initImageNotification(remoteMessage, notifyIntent, extraData)
            } else {
                initDefaultNotification(remoteMessage, notifyIntent,extraData)
            }
        } catch (e: Exception) {
            Log.v("tokenFromService", e.message +">>>")
            e.printStackTrace()
        }
    }


    private fun initImageNotification(
        remoteMessage: RemoteMessage,
        notifyIntent: Intent,
        extraData: ExtraPayLoad
    ) {

        try {

            var bitmap: Bitmap = getBitmapFromURL(extraData.image)!!


            val notifyPendingIntent =
                PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val mBuilder =
                NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(remoteMessage.data["title"])
//                .setContentText(remoteMessage.data["body"])
                    .setStyle(
                        NotificationCompat.BigPictureStyle()
                            .bigPicture(bitmap)
                            .bigLargeIcon(null).setSummaryText(remoteMessage.data["body"])
                    )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setContentIntent(notifyPendingIntent)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = getString(R.string.app_name)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(name, name, importance)
                val notificationManager =
                    getSystemService(
                        NotificationManager::class.java
                    )
                notificationManager.createNotificationChannel(channel)
                mBuilder.setChannelId(name)
                notificationManager.notify(
                    SystemClock.uptimeMillis().toInt(),
                    mBuilder.build()
                )
            } else {
                val notificationManager =
                    NotificationManagerCompat.from(this)
                notificationManager.notify(
                    SystemClock.uptimeMillis().toInt(),
                    mBuilder.build()
                )
            }
        } catch (e: Exception) {
            Log.v("tokenFromService", e.message + ">>>")
            e.printStackTrace()
        }
    }


    private fun initDefaultNotification(
        remoteMessage: RemoteMessage,
        notifyIntent: Intent,
        extraData: ExtraPayLoad
    ) {

        try {
            val notifyPendingIntent =
                PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val mBuilder =
                NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(remoteMessage.data["title"])
                    .setStyle(NotificationCompat.BigTextStyle()
                        .bigText(remoteMessage.data["body"]))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setContentIntent(notifyPendingIntent)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = getString(R.string.app_name)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(name, name, importance)
                val notificationManager =
                    getSystemService(
                        NotificationManager::class.java
                    )
                notificationManager.createNotificationChannel(channel)
                mBuilder.setChannelId(name)
                notificationManager.notify(
                    SystemClock.uptimeMillis().toInt(),
                    mBuilder.build()
                )
            } else {
                val notificationManager =
                    NotificationManagerCompat.from(this)
                notificationManager.notify(
                    SystemClock.uptimeMillis().toInt(),
                    mBuilder.build()
                )
            }
        } catch (e: Exception) {
            Log.v("tokenFromService", e.message + ">>>")
            e.printStackTrace()
        }
    }


    private fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            // Log exception
            Log.v("IOException", e.toString())
            null
        }
    }
}
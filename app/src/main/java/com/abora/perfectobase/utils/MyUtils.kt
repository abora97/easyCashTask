package com.abora.perfectobase.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.*
import android.content.ClipboardManager
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.abora.perfectobase.BuildConfig
import com.abora.perfectobase.R
import com.abora.perfectobase.databinding.DialogConfirmBinding
import com.abora.perfectobase.ui.splash.SplashActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


object MyUtils {

    fun Activity.myToast(msg: String) {
        var dialog: AlertDialog? = null

        var view = DialogConfirmBinding.inflate(LayoutInflater.from(this))

        view.txtMassage.text = msg

        view.btnOk.setOnClickListener {
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }
        view.btnCancel.visibility = View.GONE


        dialog = AlertDialog.Builder(this)
            .setView(view.root)
            .setCancelable(false)
            .show()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


    }


    fun Fragment.myToast(msg: String) {
        var dialog: AlertDialog? = null

        var view = DialogConfirmBinding.inflate(LayoutInflater.from(requireContext()))


        view.txtMassage.text = msg

        view.btnOk.setOnClickListener {
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }
        view.btnCancel.visibility = View.GONE


        dialog = AlertDialog.Builder(requireContext())
            .setView(view.root)
            .setCancelable(false)
            .show()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    fun AppCompatActivity.confirmDialog(msg: String, confirm: (AlertDialog?) -> Unit) {
        var dialog: AlertDialog? = null

        var view = DialogConfirmBinding.inflate(LayoutInflater.from(this))


        view.txtMassage.text = msg

        view.btnOk.setOnClickListener {
            confirm.invoke(dialog)
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }

        view.btnCancel.setOnClickListener {
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }
        view.btnCancel.visibility = View.GONE


        dialog = AlertDialog.Builder(this)
            .setView(view.root)
            .setCancelable(false)
            .show()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


    }

    fun Fragment.confirmDialog(msg: String, confirm: (AlertDialog?) -> Unit) {

        var dialog: AlertDialog? = null

        var view = DialogConfirmBinding.inflate(LayoutInflater.from(requireContext()))


        view.txtMassage.text = msg

        view.btnOk.setOnClickListener {
            confirm.invoke(dialog)
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }
        view.btnCancel.setOnClickListener {
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }


        dialog = AlertDialog.Builder(requireContext())
            .setView(view.root)
            .setCancelable(false)
            .show()


        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


    }

    fun Activity.myToastAction(msg: String, confirm: (AlertDialog) -> Unit) {

        var dialog: AlertDialog? = null

        var view = DialogConfirmBinding.inflate(LayoutInflater.from(this))


        view.txtMassage.text = msg

        view.btnOk.setOnClickListener {
            confirm.invoke(dialog!!)
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }
        view.btnCancel.visibility = View.GONE


        dialog = AlertDialog.Builder(this)
            .setView(view.root)
            .setCancelable(false)
            .show()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    fun Fragment.myToastAction(msg: String, confirm: (AlertDialog) -> Unit) {

        var dialog: AlertDialog? = null

        var view = DialogConfirmBinding.inflate(LayoutInflater.from(requireContext()))


        view.txtMassage.text = msg

        view.btnOk.setOnClickListener {
            confirm.invoke(dialog!!)
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }
        view.btnCancel.visibility = View.GONE


        dialog = AlertDialog.Builder(requireContext())
            .setView(view.root)
            .setCancelable(false)
            .show()


        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    fun Fragment.myToastAction2Button(msg: String, confirm: (AlertDialog) -> Unit) {

        var dialog: AlertDialog? = null

        var view = DialogConfirmBinding.inflate(LayoutInflater.from(requireContext()))


        view.txtMassage.text = msg

        view.btnOk.setOnClickListener {
            confirm.invoke(dialog!!)
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }
        view.btnCancel.setOnClickListener {
            dialog?.dismiss()
        }


        dialog = AlertDialog.Builder(requireContext())
            .setView(view.root)
            .setCancelable(false)
            .show()


        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    fun Activity.myToastAction2Button(msg: String, confirm: (AlertDialog) -> Unit) {

        var dialog: AlertDialog? = null

        var view = DialogConfirmBinding.inflate(LayoutInflater.from(this))


        view.txtMassage.text = msg
        view.txtTitle.text = "تنبية"

        view.btnOk.setOnClickListener {
            confirm.invoke(dialog!!)
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }
        view.btnCancel.setOnClickListener {
            dialog?.dismiss()
        }


        dialog = AlertDialog.Builder(this)
            .setView(view.root)
            .setCancelable(false)
            .show()


        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    fun Activity.myToast(
        title: String,
        msg: String,
        btn1: String,
        btn2: String,
        confirm: (AlertDialog) -> Unit
    ) {

        var dialog: AlertDialog? = null

        var view = DialogConfirmBinding.inflate(LayoutInflater.from(this))


        view.txtMassage.text = msg
        view.txtTitle.text = title

        view.btnOk.text = btn1
        view.btnCancel.text = btn2
        view.btnOk.setOnClickListener {
            confirm.invoke(dialog!!)
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }
        view.btnCancel.setOnClickListener {
            dialog?.dismiss()
        }


        dialog = AlertDialog.Builder(this)
            .setView(view.root)
            .setCancelable(false)
            .show()


        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    }


    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    fun ImageView.loadImg(url: String) {
        if (!TextUtils.isEmpty(url))
            Picasso.get().load(url).into(this)
    }

    fun TextView.changePartTextColor(
        text: String,
        split: String,
        color: Int,
        spanClick: Boolean = false,
        textClick: () -> Unit = {}
    ) {

        val startIndex = text.indexOf(split)

        if (startIndex == -1)
            return

        val span = SpannableString(text)

        if (spanClick) {
            span.setSpan(
                object : ClickableSpan() {
                    override fun onClick(view: View) {
                        textClick.invoke()
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        ds.isUnderlineText = false
                    }
                }, text.indexOf(split), text.length,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
            movementMethod = LinkMovementMethod.getInstance();
        }

        span.setSpan(
            ForegroundColorSpan(color), text.indexOf(split), text.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        this.text = span
    }

    fun AppCompatCheckBox.changePartTextColorBox(
        text: String,
        split: String,
        color: Int,
        textClick: (View) -> Unit = {}
    ) {

        val startIndex = text.indexOf(split)

        if (startIndex == -1)
            return

        val span = SpannableString(text)

        span.setSpan(
            object : ClickableSpan() {
                override fun onClick(view: View) {
                    textClick.invoke(view)
                }
            }, text.indexOf(split), text.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        movementMethod = LinkMovementMethod.getInstance();


        span.setSpan(
            ForegroundColorSpan(color), text.indexOf(split), text.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        this.text = span

        this.setOnClickListener {
            textClick.invoke(it)
        }
    }

    fun TextView.changeWordColor(word: String, color: Int, click: () -> Unit) {

        val startIndex = text.indexOf(word)

        if (startIndex == -1)
            return

        val span = SpannableString(text)

        span.setSpan(
            object : ClickableSpan() {
                override fun onClick(view: View) {
                    click.invoke()
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = false
                }
            }, startIndex, startIndex + word.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

        span.setSpan(
            ForegroundColorSpan(color), startIndex, startIndex + word.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )


        this.text = span

        movementMethod = LinkMovementMethod.getInstance();

    }

    fun Activity.makeStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                } else {
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                }
                statusBarColor = Color.TRANSPARENT
            }
        }
    }

    fun Activity.makeStatusBarVisible() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                decorView.systemUiVisibility = 0
                statusBarColor = resources.getColor(R.color.black)
            }
        }
    }


    fun String.openImageGallery(context: Context) {
        try {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.setDataAndType(Uri.parse(this), "image/*")
            intent.setPackage("com.google.android.apps.photos")
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.setDataAndType(Uri.parse(this), "image/*")
            context.startActivity(intent)
        }
    }

    suspend fun File.createThumb(): Bitmap {

        return CoroutineScope(Dispatchers.IO).async {
            Bitmap.createScaledBitmap(BitmapFactory.decodeFile(path), 100, 100, false)
        }.await()
    }

    inline fun <reified T> SharedPreferences.getObject(key: String): T? {

        val objectJson = getString(key, "")

        if (!objectJson.isNullOrEmpty()) {
            return Gson().fromJson(objectJson, T::class.java)
        }

        return null
    }

    fun <T> SharedPreferences.saveObject(key: String, objectToSave: T) {

        CoroutineScope(Dispatchers.Default).launch {
            val objectJson = Gson().toJson(objectToSave)

            if (!objectJson.isNullOrEmpty())
                edit().putString(key, objectJson).apply()
        }

    }

    fun Activity.openLocationMap(lat: String, lng: String) {
        openMap(lat, lng, this)
    }

    fun Fragment.openLocationMap(lat: String, lng: String) {
        openMap(lat, lng, this.requireActivity())
    }

    private fun openMap(lat: String, lng: String, context: Context) {
        if (appInstalledOrNot("com.google.android.apps.maps", context)) {
            val gmmIntentUri = Uri.parse("geo:${lat},${lng}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            context.startActivity(mapIntent)
        } else {
            val uri = "http://maps.google.com/maps?q=loc:$lat,$lng"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(intent)
            Log.d("openMap", "google map not found")
        }
    }

    private fun appInstalledOrNot(uri: String, context: Context): Boolean {
        val pm: PackageManager = context.packageManager
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return false
    }

    fun Activity.openActivity(
        open: Class<*>,
        data: Intent? = null,
        mustLogin: Boolean = false,
        loginCheck: Boolean = false
    ) {
        openActivityManager(open, data, mustLogin, loginCheck, this)
    }

    fun Fragment.openActivity(
        open: Class<*>,
        data: Intent? = null,
        mustLogin: Boolean = false,
        loginCheck: Boolean = false
    ) {
        openActivityManager(open, data, mustLogin, loginCheck, requireActivity())
    }

    private fun openActivityManager(
        open: Class<*>,
        data: Intent? = null,
        mustLogin: Boolean = false,
        loginCheck: Boolean = false,
        activity: Activity
    ) {

        if (mustLogin && !loginCheck) {
            activity.myToastAction2Button(activity.resources.getString(R.string.please_login_first)) {
                activity.openActivity(SplashActivity::class.java)
            }
        } else {
            var intent = Intent(activity, open)
            if (data != null) {
                intent.putExtras(data)
            }
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.slide_up, R.anim.no_change)
        }
    }

    fun setRecyclerAnimation(mRecycler: RecyclerView) {
        mRecycler.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(mRecycler.context, R.anim.item_recycler_anim)
        mRecycler.scheduleLayoutAnimation()
        (mRecycler.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    fun isAppInstalled(packageName: String, context: Context): Boolean {
        return try {
            val packageManager = context.packageManager
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    fun Activity.copyText(text: String) {
        copyTextOp(text, this)
    }

    fun Fragment.copyText(text: String) {
        copyTextOp(text, requireContext())
    }

    fun copyTextOp(text: String, context: Context) {
        val clipboard: ClipboardManager? =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val clip = ClipData.newPlainText("", text)
        clipboard?.setPrimaryClip(clip)
        Toast.makeText(context, context.getString(R.string.textCopy), Toast.LENGTH_SHORT).show()
    }

    fun Activity.shareApp() {
        shareAppOperator(this)
    }

    fun Fragment.shareApp() {
        shareAppOperator(requireActivity())
    }

    private fun shareAppOperator(activity: Activity) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                activity.resources.getString(R.string.app_name)
            )
            val shareMsg = ""
            val appLink =
                "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
            shareIntent.putExtra(Intent.EXTRA_TEXT, "\n\n" + shareMsg + "\n\n" + appLink)
            activity.startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
            Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun Activity.shareContent(shareData: String, shareImg: String) {
        if (shareImg.isNullOrEmpty()) {
            shareContentOperator(this, shareData)
        } else {
            shareContentWithImgOperator(this, shareData, shareImg)
        }
    }

    fun Fragment.shareContent(shareData: String, shareImg: String) {
        if (shareImg.isNullOrEmpty()) {
            shareContentOperator(requireActivity(), shareData)
        } else {
            shareContentWithImgOperator(requireActivity(), shareData, shareImg)
        }

    }

    private fun shareContentWithImgOperator(
        activity: Activity,
        shareData: String,
        shareImg: String
    ) {
        Picasso.get().load(shareImg).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {

                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    shareData
                )
                sendIntent.putExtra(
                    Intent.EXTRA_STREAM,
                    getLocalBitmapUri(bitmap, activity, shareData)
                )
                sendIntent.type = "image/*"
                activity.startActivity(sendIntent)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                shareContentOperator(activity, shareData)
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        })

    }

    private fun shareContentOperator(activity: Activity, shareData: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            shareData
        )
        sendIntent.type = "text/plain"
        activity.startActivity(sendIntent)
    }

    private fun getLocalBitmapUri(bmp: Bitmap?, activity: Activity, shareData: String): Uri? {
        var bmpUri: Uri? = null
        try {
            val file = File(
                activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "share_image_" + System.currentTimeMillis() + ".png"
            )
            val out = FileOutputStream(file)
            bmp?.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.close()
            bmpUri = Uri.fromFile(file)
        } catch (e: IOException) {
            e.printStackTrace()
            shareContentOperator(activity, shareData)
        }
        return bmpUri
    }


    fun Activity.openBrowser(target: String) {
        openBrowserEngine(target, this)
    }

    fun Fragment.openBrowser(target: String) {
        openBrowserEngine(target, this.activity)
    }

    private fun openBrowserEngine(targetUrl: String, activity: Activity?) {
        var url = targetUrl
        if (!targetUrl.startsWith("http://") && !targetUrl.startsWith("https://")) {
            url = "http://$targetUrl"
        }

        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity?.startActivity(browserIntent)
    }

    fun Activity.makeStatusBarWhite() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

                statusBarColor = resources.getColor(R.color.white)
            }
        }
    }

    fun Activity.makeStatusBarColorPrimary() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                decorView.systemUiVisibility = 0
                statusBarColor = resources.getColor(R.color.colorPrimary)
            }
        }
    }

    fun BitMapToString(bitmap: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun StringToBitMap(encodedString: String?): Bitmap? {
        return try {
            val encodeByte: ByteArray = Base64.decode(encodedString, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
        } catch (e: Exception) {
            e.message
            null
        }
    }


    fun Activity.phoneCall(phoneNum: String) {
        if (phoneNum != null)
            phoneCallEngine(phoneNum, this)
    }

    fun Fragment.phoneCall(phoneNum: String) {
        if (phoneNum != null)
            phoneCallEngine(phoneNum, requireActivity())
    }

    private fun phoneCallEngine(phoneNum: String, activity: Activity?) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${phoneNum}")
        activity?.startActivity(intent)
    }

    fun Activity.sendEmail(email: String) {
        if (email != null)
            sendEmailEngine(email, this)
    }

    fun Fragment.sendEmail(email: String) {
        if (email != null)
            sendEmailEngine(email, requireActivity())
    }

    private fun sendEmailEngine(email: String, activity: Activity?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        activity?.startActivity(Intent.createChooser(intent, "Choose Email Client..."))
    }

    fun Activity.openWhatsWithNum(phoneNum: String) {
        if (phoneNum != null)
            openWhatsAppWithNumEngine(phoneNum, this)
    }

    fun Fragment.openWhatsWithNum(phoneNum: String) {
        if (phoneNum != null)
            openWhatsAppWithNumEngine(phoneNum, requireActivity())
    }

    private fun openWhatsAppWithNumEngine(phoneNum: String, activity: Activity?) {
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNum&text= ")

        val sendIntent = Intent(Intent.ACTION_VIEW, uri)

        activity?.startActivity(sendIntent)
    }

}



package com.abora.perfectobase.utils

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.net.ParseException
import android.net.Uri
import android.os.CountDownTimer
import android.text.Html
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

import android.graphics.BitmapFactory
import com.abora.perfectobase.R

import com.bumptech.glide.Glide
import java.io.File


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("error")
    fun setError(textInputLayout: TextInputLayout, error: String?) {
        textInputLayout.error = error
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:cardBg")
    fun cardBg(cardView: CardView, color: String?) {
        color ?: return
        cardView.setCardBackgroundColor(Color.parseColor(color))
    }


    @JvmStatic
    @BindingAdapter("app:errorText")
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        view.error = errorMessage
    }

    @JvmStatic
    @BindingAdapter("app:errorText")
    fun setErrorMessage(view: EditText, errorMessage: String?) {
        view.error = errorMessage
    }

    @JvmStatic
    @BindingAdapter("app:htmlText")
    fun htmlText(textView: TextView, text: String?) {
        text ?: return
        textView.text = Html.fromHtml(text)
    }


    @JvmStatic
    @BindingAdapter("app:textChangedListener")
    fun pinTextChange(
        editText: PinEntryEditText,
        textWatcher: PinEntryEditText.OnPinEnteredListener
    ) {
        editText.setOnPinEnteredListener(textWatcher)
    }


    @JvmStatic
    @BindingAdapter("app:rateValue")
    fun setRating(ratingBar: RatingBar, mVoteAverage: Double) {
        mVoteAverage
        ratingBar.rating = mVoteAverage.toFloat()
    }

    @JvmStatic
    @BindingAdapter("app:setTextColor")
    fun setTextColor(textView: TextView, color: String?) {
        color ?: return
        textView.setTextColor(Color.parseColor(color))
    }

    @JvmStatic
    @BindingAdapter("app:setBackGroundColor")
    fun setTextColor(view: View, color: String?) {
        color ?: return
        view.setBackgroundColor(Color.parseColor(color))
    }

    @JvmStatic
    @BindingAdapter("app:setUnderLine")
    fun setUnderLine(textView: TextView, set: Boolean) {
        textView.setPaintFlags(textView.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
    }

    @JvmStatic
    @BindingAdapter("app:setCenterLine")
    fun setCenterLine(textView: TextView, set: Boolean) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

    }

    @JvmStatic
    @BindingAdapter("app:currency", "app:amount")
    fun setCurrencyAndAmount(
        textView: TextView,
        currency: String,
        amount: Double
    ) {
        textView.text = String.format(Locale.US, "%.2f", amount) + " " + currency
    }


    @JvmStatic
    @BindingAdapter("app:timer")
    fun timer(textView: TextView, dateString: String?) {

        if (TextUtils.isEmpty(dateString)) {
            textView.visibility = View.GONE
            return
        }

        if (dateString?.contains("visible") == true) {
            return
        }

        val simplreDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = simplreDateFormat.parse(dateString)
        val milliSeconds = date.time - Calendar.getInstance().time.time

        val days = TimeUnit.MILLISECONDS.toDays(milliSeconds)

        if (days > 0) {
            textView.text = "$days ${textView.context.getString(R.string.left)}"
        } else {
            object : CountDownTimer(milliSeconds, 1000) {
                override fun onFinish() {
                    textView.text = "Expired"
                }

                override fun onTick(tick: Long) {

                    textView.text = String.format(
                        "%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(tick),
                        TimeUnit.MILLISECONDS.toMinutes(tick) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(tick) % 60
                    )
                }
            }.start()
        }

    }


    @JvmStatic
    @BindingAdapter("colorForText")
    fun colorForText(textView: TextView, color: String?) {

        color ?: return

        textView.setTextColor(Color.parseColor(color))
    }

    @JvmStatic
    @BindingAdapter("app:dateFormat")
    fun dateFormat(textView: TextView, date: String?) {
        date ?: return
        val strDate = date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        var convertedDate: Date? = Date()
        try {
            convertedDate = dateFormat.parse(strDate)
            val sdfnewformat = SimpleDateFormat("MMM dd yyyy")
            val finalDateString = sdfnewformat.format(convertedDate)
            textView.text = finalDateString
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }


    @JvmStatic
    @BindingAdapter(value = ["loadImg", "placeholder"], requireAll = false)
    fun loadImg(imageView: ImageView, url: String?, placeHolder: Drawable?) {
        if (!TextUtils.isEmpty(url)) {
            var placeHolderId = placeHolder

            if (placeHolderId == null) {
                placeHolderId =
                    imageView.context.resources.getDrawable(R.drawable.ic_placeholder_square)
            }

//            Picasso.get()
//                .load(url)
//                .error(placeHolderId!!)
//                .placeholder(imageView.context.resources.getDrawable(R.drawable.placeholder))
//                .into(imageView)
            Glide
                .with(imageView.context)
                .load(url)
                .placeholder(imageView.context.resources.getDrawable(R.drawable.ic_placeholder_square))
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["loadImgLocal"])
    fun loadImgLocal(imageView: ImageView, url: String?) {
        val imgFile = File(url)

        if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath())
            imageView.setImageBitmap(myBitmap)
        }
    }

//    @JvmStatic
//    @BindingAdapter(value = ["loadVideo"])
//    fun loadVideo(imageView: BetterVideoPlayer, url: String?) {
//        if (!TextUtils.isEmpty(url)) {
//            imageView.setCaptions(Uri.parse(url), CaptionsView.SubMime.SUBRIP)
//        }
//    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUri(view: ImageView, imageUri: String?) {
        if (imageUri == null) {
            view.setImageURI(null)
        } else {
            view.setImageURI(Uri.parse(imageUri))
        }
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUri(view: ImageView, imageUri: Uri?) {
        view.setImageURI(imageUri)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageDrawable(view: ImageView, drawable: Drawable?) {
        view.setImageDrawable(drawable)
    }
}

@BindingAdapter("app:priceOfferLine")
fun priceOfferLine(textView: TextView, bool: Boolean) {
    if (bool) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

}

@BindingAdapter("setMarquee")
fun setMarquee(textView: TextView, selected: Boolean) {
    textView.ellipsize=TextUtils.TruncateAt.MARQUEE
    textView.isSingleLine=true
    textView.marqueeRepeatLimit = -1
    textView.setHorizontallyScrolling(true)
    textView.isSelected = selected
}

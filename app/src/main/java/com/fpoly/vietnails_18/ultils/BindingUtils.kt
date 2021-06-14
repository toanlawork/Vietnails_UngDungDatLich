package com.fpoly.vietnails_18.ultils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.fpoly.vietnails_18.BuildConfig
import com.fpoly.vietnails_18.R
import java.io.File
import java.util.*


@SuppressLint("CheckResult")
@BindingAdapter(
        value = ["loadImage", "localBackground", "placeholder", "centerCrop", "fitCenter", "circleCrop",
            "cacheSource", "animation"],
        requireAll = false
)
fun ImageView.loadImage(
        imageUrl: String? = "",
        localImage: Drawable? = null,
        placeHolder: Drawable? = ContextCompat.getDrawable(this.context, R.drawable.place_holder),
        centerCrop: Boolean = false,
        fitCenter: Boolean = false,
        circleCrop: Boolean = false,
        isCacheSource: Boolean = false,
        animation: Boolean = false
) {
    if (TextUtils.isEmpty(imageUrl)) {
        setImageDrawable(placeHolder)
        return
    }

    val requestBuilder: RequestBuilder<Drawable>
    val localImageId =
            context.resources.getIdentifier(imageUrl, "drawable", BuildConfig.APPLICATION_ID)
    requestBuilder = if (localImageId != 0) {
        // Load image from local
        localImage?.let { background = localImage }
        Glide.with(context).load(localImageId)
                .error(ContextCompat.getDrawable(this.context, R.drawable.place_holder))

    } else {
        // image is not in local, but may be in server.
        Glide.with(context).load(imageUrl)
                .error(ContextCompat.getDrawable(this.context, R.drawable.place_holder))
    }
    val diskCacheStrategy = if (isCacheSource) {
        DiskCacheStrategy.DATA
    } else {
        DiskCacheStrategy.RESOURCE
    }
    val requestOptions =
            RequestOptions().diskCacheStrategy(diskCacheStrategy).placeholder(placeHolder)
    if (!animation) {
        requestOptions.dontAnimate()
    }
    if (centerCrop) {
        requestOptions.centerCrop()
    }
    if (fitCenter) {
        requestOptions.fitCenter()
    }
    if (circleCrop) {
        requestOptions.circleCrop()
    }
    val file = File(imageUrl ?: return)
    if (file.exists()) {
        requestOptions.signature(ObjectKey(file.lastModified().toString()))
    }
    requestBuilder.apply(requestOptions).into(this)
}

@BindingAdapter("isSelected")
fun setSelected(view: View, selected: Boolean) {
    view.isSelected = selected
}

@BindingAdapter("loadImageDrawable")
fun ImageView.loadImageDrawable(id: Int) {
    setImageResource(id)
}

fun SortedMap<String, Int>.updateItem(key: String, value: Int) {
    if (this.containsKey(key)) {
        this[key] = this[key]?.plus(value)
    } else {
        this[key] = value
    }
}


@BindingAdapter("textLimitLengthEllipsize")
fun TextView.setTextLimitLengthEllipsize(text: String?) {
    if (text == null) {
        setText("")
        return
    }
    val newText = if (text.length > 8) {
        text.replaceRange(8, text.length, "...")
    } else {
        text
    }
    setText(newText)
}

@BindingAdapter("imageResource")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}

@BindingAdapter(
        value = ["lengthExpect", "text"],
        requireAll = false
)
fun TextView.setTextLimitLengthEllipsizeE(lengthExpect: Int, text: String?) {
    if (text == null) {
        setText("")
        return
    }
    val newText = if (text.length > lengthExpect) {
        text.replaceRange(lengthExpect, text.length, "...")
    } else {
        text
    }
    setText(newText)
}

@BindingAdapter("android:customHeight")
fun setLayoutHeight(view: View, height: Float) {
    view.layoutParams = view.layoutParams.apply { this.height = height.toInt() }
}
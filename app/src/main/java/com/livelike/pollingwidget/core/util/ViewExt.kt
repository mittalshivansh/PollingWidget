package com.livelike.pollingwidget.core.util

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.livelike.pollingwidget.R
import java.io.File

/**
 * Created by shivanshmittal on 2019-06-22.
 */


/**
 * ImageView extension to load image by using Glide
 */
fun ImageView.loadImage(imagePath: String?) {
    Glide.with(this).load(if (imagePath!!.contains("http")) imagePath else File(imagePath))
        .placeholder(R.drawable.ic_dummy_option)
        .override(this.context.convertDP2Px(150), this.context.convertDP2Px(150))
        .into(this)
}

fun Context.convertDP2Px(dp: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)
        .toInt()
}



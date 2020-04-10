/*
 * Copyright (c) 2020.  Gallup, Inc. All rights reserved
 * ViewExtenstion.kt
 */

package com.example.newtestapplication.data

import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.example.newtestapplication.R
import java.util.*
import java.util.concurrent.TimeUnit


fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, androidx.lifecycle.Observer(body))


fun ImageView.loadImage(filePath: String, placeHolder: Int? = null) {

    val requestBuilder = Glide.with(this).load(filePath)
    if (placeHolder != null)
        requestBuilder.placeholder(placeHolder)
    else {
        requestBuilder.placeholder(R.drawable.ic_launcher_background)
    }
    if (placeHolder != null)
        requestBuilder.error(placeHolder)
    else {
        requestBuilder.error(R.drawable.ic_launcher_background)
    }
    requestBuilder.into(this)
}

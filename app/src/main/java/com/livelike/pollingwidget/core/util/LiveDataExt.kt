package com.livelike.pollingwidget.core.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

/**
 * Created by shivanshmittal on 2019-06-26.
 */


fun <T> LiveData<T>.distinctUntilChanged():LiveData<T>{
    return Transformations.distinctUntilChanged(this)
}
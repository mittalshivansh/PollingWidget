package com.livelike.pollingwidget

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by shivanshmittal on 2019-06-23.
 */

class PollingApp : Application(){

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    companion object {
        private  lateinit var instance: PollingApp
        fun get():PollingApp{
            return instance
        }
    }

}
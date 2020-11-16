package com.lancer.codereview

import android.app.Application
import android.content.Context

/**
 *
 *created by Lancer on 2020/11/16
 *desc
 */
class BaseApplication : Application() {
    private lateinit var context: Context
    override fun onCreate() {
        super.onCreate()
        context=this
    }
}
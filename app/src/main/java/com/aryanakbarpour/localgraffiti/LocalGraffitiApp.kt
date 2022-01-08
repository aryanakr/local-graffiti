package com.aryanakbarpour.localgraffiti

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.aryanakbarpour.localgraffiti.di.AppContainer

class LocalGraffitiApp : Application() {

    //private val apiKey = applicationInfo.metaData["ApiKey"]

    val appContainer = AppContainer()
}
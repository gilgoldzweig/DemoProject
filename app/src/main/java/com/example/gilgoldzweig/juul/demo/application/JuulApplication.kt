package com.example.gilgoldzweig.juul.demo.application

import android.app.Application
import com.example.gilgoldzweig.juul.demo.BuildConfig
import com.example.gilgoldzweig.juul.demo.modules.di.DaggerNetworkComponent
import com.example.gilgoldzweig.juul.demo.modules.di.NetworkComponent
import com.example.gilgoldzweig.movies.extensions.Timber
import goldzweigapps.com.core.preferences.GlobalSharedPreferences

class JuulApplication: Application() {

    companion object {
        lateinit var networkComponent: NetworkComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            //Production tree will be planted with connection to firebase and crashlytics
        }

        GlobalSharedPreferences.initialize(this)

        networkComponent = DaggerNetworkComponent.create()

    }
}
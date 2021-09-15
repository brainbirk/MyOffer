package dk.shantech.myoffer.ui.main

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dk.shantech.myoffer.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
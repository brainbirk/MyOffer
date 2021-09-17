package dk.shantech.myoffer.di

import android.content.Context
import android.content.pm.PackageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dk.shantech.myoffer.BuildConfig
import javax.inject.Singleton

const val META_API_KEY = "com.shopgun.android.sdk.api_key"
const val META_API_SECRET = "com.shopgun.android.sdk.api_secret"

const val META_DEVELOP_API_KEY = "com.shopgun.android.sdk.develop.api_key"
const val META_DEVELOP_API_SECRET = "com.shopgun.android.sdk.develop.api_secret"


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAppKey(@ApplicationContext context: Context): AppInfo {
        val packageName = context.packageName
        val metaData = context.packageManager.getApplicationInfo(
            packageName,
            PackageManager.GET_META_DATA
        ).metaData
        val apiKey = if (BuildConfig.DEBUG && metaData.containsKey(META_DEVELOP_API_KEY)) {
            metaData.getString(META_DEVELOP_API_KEY)
        } else {
            metaData.getString(META_API_KEY)
        }

        val apiSecret = if (BuildConfig.DEBUG && metaData.containsKey(META_DEVELOP_API_SECRET)) {
            metaData.getString(META_DEVELOP_API_SECRET)
        } else {
            metaData.getString(META_API_SECRET)
        }
        return AppInfo(appVersion = BuildConfig.VERSION_NAME, apiKey = apiKey, apiSecret = apiSecret)
    }



}

data class AppInfo(val appVersion: String, val apiKey: String?, val apiSecret: String?)
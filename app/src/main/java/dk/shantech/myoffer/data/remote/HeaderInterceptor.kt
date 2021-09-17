package dk.shantech.myoffer.data.remote

import androidx.core.os.LocaleListCompat
import dk.shantech.myoffer.di.AppInfo
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

private const val CLIENT_VERSION = "X-Client-Version"
private const val API_KEY = "X-Api-Key"
private const val API_SECRET = "X-Api-Secret"
private const val LANGUAGE = "Accept-Language"
private const val TOKEN = "x-Token"

@Singleton
class HeaderInterceptor @Inject constructor(private val appInfo: AppInfo) : Interceptor {

    private var token: String = ""

    fun setToken(token: String) {
        this.token = token
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val headers = request.headers
        val newRequest = request.newBuilder()

        newRequest.addHeaderIfNotExist(
            key = CLIENT_VERSION,
            value = appInfo.appVersion,
            headers = headers
        )

        newRequest.addHeaderIfNotExist(
            key = API_KEY,
            value = appInfo.apiKey,
            headers = headers
        )

        newRequest.addHeaderIfNotExist(
            key = API_SECRET,
            value = appInfo.apiSecret,
            headers = headers
        )

        newRequest.addHeaderIfNotExist(
            key = LANGUAGE,
            value = LocaleListCompat.getAdjustedDefault().toLanguageTags(),
            headers = headers
        )

        if (headers.notContains(TOKEN) && token.isNotEmpty()) {
            newRequest.addHeader(TOKEN, token)
        }

     return chain.proceed(newRequest.build())
    }
}

private fun Request.Builder.addHeaderIfNotExist(key: String, value: String?, headers: Headers) {
    if (value.isNullOrEmpty()) throw Exception("Value for $key is missing")

    if (headers[key].isNullOrEmpty()) {
        this.addHeader(key, value)
    }
}

private fun Headers.notContains(key: String) : Boolean {
    return this[key].isNullOrEmpty()
}
package com.wordy.getrandomapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.InputStream

class RestClient {

    private val randomImageUrl = "https://picsum.photos/"
    var client: OkHttpClient? = null

    fun initClient(httpClient: OkHttpClient): RestClient {
        this.client = httpClient
        return this
    }

    fun getRandomImage(url: String): Bitmap {
        val request = Request.Builder()
            .url(url + (200..1000).random() + "/" + (200..1000).random())
            .get()
            .build()

        client?.newCall(request)?.execute().use { response ->
            return BitmapFactory.decodeStream(response?.body!!.byteStream())
        }
    }

    fun getUrlForRandomImage(): String {
        val request = Request.Builder()
            .url(randomImageUrl)
            .get()
            .build()

        client?.newCall(request)?.execute().use { response ->
            Log.d("RANDOM_IMAGE", response?.body.toString())
            return response?.request?.url.toString()
        }
    }

}
package com.wordy.getrandomapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.InputStream
import java.lang.Exception

class RestClient(private val client: OkHttpClient) {

    private val randomImageUrl = "https://picsum.photos/"

    fun getRandomBitmap(): Bitmap? {
        val url = getUrlForRandomImage()
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        client.newCall(request).execute().use { response ->
            return try {
                BitmapFactory.decodeStream(response.body?.byteStream())
            } catch (e: Exception) {
                Log.d(GetRandApplication.Constants.TAG, e.message.toString())
                null
            }
        }
    }

    private fun getUrlForRandomImage(): String {
        val request = Request.Builder()
            .url(randomImageUrl + (200..1000).random() + "/" + (200..1000).random())
            .get()
            .build()

        client.newCall(request).execute().use { response ->
            return response.request.url.toString()
        }
    }

}
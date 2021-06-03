package com.wordy.getrandomapplication

import android.graphics.Bitmap
import io.reactivex.Single
import io.reactivex.SingleObserver
import okhttp3.OkHttpClient

class MainRepository {

    val restClient = RestClient(OkHttpClient())

    fun getRandomBitmap(): Single<Bitmap?> {
        return object : Single<Bitmap?>() {
            override fun subscribeActual(observer: SingleObserver<in Bitmap?>) {
                observer.onSuccess(
                    restClient.getRandomBitmap()
                )
            }
        }
    }
}
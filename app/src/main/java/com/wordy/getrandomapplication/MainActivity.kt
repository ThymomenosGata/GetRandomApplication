package com.wordy.getrandomapplication

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import java.io.InputStream
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getRandImage()
    }

    fun nextImage(view: View) {
        getRandImage()
    }

    @SuppressLint("SetTextI18n")
    fun getRandImage() {
        val client = RestClient().initClient(OkHttpClient())
        thread {
            val imageUrl = client.getUrlForRandomImage()
            val ins = client.getRandomImage(imageUrl)
            this@MainActivity.runOnUiThread{
                randImage.setImageBitmap(ins)
                imageSize.text = "size: ${ins.byteCount} Byte"
            }
        }

    }

}
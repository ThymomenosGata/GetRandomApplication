package com.wordy.getrandomapplication.ui.main

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.wordy.getrandomapplication.R
import kotlinx.android.synthetic.main.activity_main.*

/*
Тестовое задание:

Необходимо написать приложение с одним экраном,
на котором отображается случайная картинка из интернета и её размер в байтах.
Картинка должна отображаться в максимально большом размере без искажения пропорций.
При нажатии на картинку должна подгрузиться другая случайная картинка.
Код приложения должен быть рассчитан на дальнейшее развитие, пускай даже архитектура будет избыточной.
Использовать можно любые библиотеки, кроме Picasso и их аналогов,
т.е. без использования библиотек специально предназначенных для загрузки картинок.
*/

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getIsSearchingState().observe(this,
            {
                card_warning.visibility = if (it) View.VISIBLE else View.GONE
                randImage.isFocusable = !it
                randImage.isClickable = !it
            }
        )

        viewModel.getError().observe(this,
            {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        )
        viewModel.getImage().observe(this,
            {
                randImage.setImageBitmap(it)
            }
        )
        viewModel.getText().observe(this,
            {
                imageSize.text = it
            }
        )
    }

    fun nextImage(view: View) {
        if (isOnline()) {
            loadImage()
        } else {
            Toast.makeText(this, "Отсутствует подключение к интернету", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadImage() {
        viewModel.loadImage()
    }

    private fun isOnline(): Boolean {
        val cm: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            cm.activeNetwork != null
        } else {
            cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        }
    }

}
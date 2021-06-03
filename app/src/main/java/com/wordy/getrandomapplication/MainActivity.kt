package com.wordy.getrandomapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
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
        loadImage()
    }

    private fun loadImage() {
        viewModel.loadImage()
    }

}
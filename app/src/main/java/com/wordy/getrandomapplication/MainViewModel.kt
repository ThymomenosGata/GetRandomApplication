package com.wordy.getrandomapplication

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private var image: MutableLiveData<Bitmap> = MutableLiveData()
    private var size: MutableLiveData<String> = MutableLiveData()
    private var isSearchingState: MutableLiveData<Boolean> = MutableLiveData()
    private var error: MutableLiveData<String> = MutableLiveData()
    private val repository = MainRepository()

    init {
        loadImage()
    }

    fun loadImage() {
        isSearchingState.value = true
        repository.getRandomBitmap()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: DisposableSingleObserver<Bitmap>() {
                override fun onSuccess(randImage: Bitmap) {
                    image.value = randImage
                    size.value = "size: ${randImage.byteCount} Byte"
                    isSearchingState.value = false
                }

                override fun onError(e: Throwable) {
                    error.value = e.message
                    size.value = "size: 0 Byte"
                    isSearchingState.value = false
                }

            })
    }

    fun getImage(): LiveData<Bitmap> {
        return image
    }

    fun getText(): LiveData<String> {
        return size
    }

    fun getIsSearchingState(): LiveData<Boolean> {
        return isSearchingState
    }

    fun getError(): LiveData<String> {
        return error
    }
}
package com.wordy.getrandomapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wordy.getrandomapplication.ui.main.MainRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainRepositoryInstrumentedTest {

    lateinit var mainRepository: MainRepository

    @Before
    fun before() {
        mainRepository = MainRepository()
    }

    @Test
    fun testMainRepository() {
        mainRepository
            .getRandomBitmap()
            .test()
            .assertComplete()
            .dispose()
    }

}
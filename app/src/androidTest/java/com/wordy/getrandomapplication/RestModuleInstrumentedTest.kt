package com.wordy.getrandomapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wordy.getrandomapplication.data.RestClient
import junit.framework.Assert.assertNotNull
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestModuleInstrumentedTest {
    lateinit var restClient: RestClient

    @Before
    fun beforeWorkingViewModel() {
        restClient = RestClient(OkHttpClient())
    }

    @Test
    fun loadFirstImage() {
        assertNotNull(restClient.getRandomBitmap())
    }
}
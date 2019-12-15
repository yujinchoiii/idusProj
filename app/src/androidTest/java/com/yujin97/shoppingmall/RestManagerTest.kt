package com.yujin97.shoppingmall

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yujin97.shoppingmall.restManager.RestManager
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.concurrent.CountDownLatch

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RestManagerTest {
    @Before
    fun setUp() {
        RestManager.init()
    }
    @Test
    fun getItemList() {
        val signal = CountDownLatch(1)
        RestManager.getItemListOf(1) { list ->
            assertNotEquals(null, list)
            signal.countDown()
        }
        signal.await()
    }
    @Test
    fun getItem() {
        val signal = CountDownLatch(1)
        RestManager.getItemInfo(1) { item ->
            assertNotEquals(null, item)
            signal.countDown()
        }
        signal.await()
    }
}

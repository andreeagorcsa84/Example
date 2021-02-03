package com.example.example

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import java.util.*

class MyService() : LifecycleService() {

    // Binder given to clients
    private val binder = LocalBinder()

    //val random = (0..100).random()

    // Random number generator
    private val mGenerator = Random()
    val randomNumber: Int
        get() = mGenerator.nextInt(100)
    val repository = MyRepository.instance

    // 1. from MyService we call setRandomNumber() to update LiveData
    // 2. The ViewModel gets the LiveData and updates the UI
    // 3. From the Activity we observe the LiveData from the UI
    fun setRandomNumber() {
        repository.setNumber(randomNumber)
    }

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so activities can call public methods
        fun getService(): MyService = this@MyService
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
}
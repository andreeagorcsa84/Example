package com.example.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyRepository private constructor(){

    // repository is singleton, in order to access the same repo instance both from Service, ViewModel and Acttivity
    // creates a new instance of the class
    private object HOLDER {
        val INSTANCE = MyRepository()
    }

    // companion object -> creates new instances of the class without using a new keyword
    // creates an instance of the class which will be used in MyService
    companion object {
        val instance: MyRepository by lazy { HOLDER.INSTANCE }
    }

    // MutableLiveData number variable
    // a random number will be displayed on the screen
    private val _number = MutableLiveData<Int>()

    // getter
    val number: LiveData<Int>
        get() = _number

    // setter
    fun setNumber(number: Int) {
        _number.value = number
    }
}
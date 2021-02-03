package com.example.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ServiceViewModelFactor(var repository: MyRepository) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}


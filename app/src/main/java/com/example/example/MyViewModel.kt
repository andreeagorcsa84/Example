package com.example.example

import androidx.lifecycle.ViewModel

class MyViewModel (repository: MyRepository): ViewModel() {

    val number = repository.number
}
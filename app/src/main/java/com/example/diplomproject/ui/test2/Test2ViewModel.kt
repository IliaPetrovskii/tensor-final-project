package com.example.diplomproject.ui.test2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Test2ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "TEST2"
    }
    val text: LiveData<String> = _text
}
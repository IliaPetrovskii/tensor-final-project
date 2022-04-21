package com.example.diplomproject.ui.test1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Test1ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "TEST1"
    }
    val text: LiveData<String> = _text
}
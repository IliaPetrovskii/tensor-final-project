package com.example.diplomproject.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diplomproject.data.database.Movie

class SearchViewModel : ViewModel() {

    var movies: MutableLiveData<List<Movie>> = MutableLiveData()
}
package com.example.diplomproject.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diplomproject.Movie

class HomeViewModel : ViewModel() {

    val movies = MutableLiveData(Movie.getMockMovies())

    fun deleteMovie(position: Int){
        movies.value = movies.value?.toMutableList()?.apply {
            removeAt(position)
        }
    }
}
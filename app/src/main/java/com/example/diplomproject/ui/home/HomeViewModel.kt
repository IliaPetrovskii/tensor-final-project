package com.example.diplomproject.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diplomproject.KpApplication
import com.example.diplomproject.data.database.MovieEntity

class HomeViewModel : ViewModel() {

    var movies: MutableLiveData<List<MovieEntity>> =
        MutableLiveData(KpApplication.database.movieDao().getAlphabetizedMovieList())

    fun deleteMovie(movie: MovieEntity) {
        KpApplication.database.movieDao().delete(movie)
    }
}
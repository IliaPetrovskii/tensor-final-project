package com.example.diplomproject

import android.app.Application
import com.example.diplomproject.data.database.MovieDatabase

class KpApplication : Application() {

    companion object {
        lateinit var instance: KpApplication
        lateinit var database: MovieDatabase
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        database = MovieDatabase.invoke(this)
    }
}
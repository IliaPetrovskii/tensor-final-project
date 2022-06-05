package com.example.diplomproject.data.database

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * from movies ORDER BY name ASC")
    fun getAlphabetizedMovieList(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieEntity: MovieEntity)

    @Delete
    fun delete(movieEntity: MovieEntity)

    @Query("DELETE FROM movies")
    fun deleteAll()
}
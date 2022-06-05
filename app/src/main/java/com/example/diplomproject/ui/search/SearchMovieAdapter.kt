package com.example.diplomproject.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diplomproject.KpApplication
import com.example.diplomproject.R
import com.example.diplomproject.data.database.Movie
import com.example.diplomproject.data.database.MovieEntity

class SearchMovieAdapter : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {

    private var movies = mutableListOf<Movie>()

    class SearchMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.poster)
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val yearTextView: TextView = itemView.findViewById(R.id.year)
        val countryTextView: TextView = itemView.findViewById(R.id.country)
        val addButton: ImageView = itemView.findViewById(R.id.add_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.search_movie_list_item, parent, false)
        return SearchMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        val movie = movies[position]
        with(holder) {
            nameTextView.text = movie.nameRu
            yearTextView.text = movie.year.toString()
            countryTextView.text = movie.countries.toString()

            Glide.with(posterImageView)
                .load(movie.posterUrl)
                .centerCrop()
                .into(posterImageView)

            addButton.setOnClickListener {
                KpApplication.database.movieDao().insert(
                    MovieEntity(
                        movie.kinopoiskId,
                        movie.nameRu,
                        movie.year,
                        movie.countries.toString(),
                        movie.posterUrl
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun reload(data: List<Movie>) {
        movies.clear()
        movies.addAll(data)
        notifyDataSetChanged()
    }
}
package com.example.diplomproject.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diplomproject.KpApplication
import com.example.diplomproject.R
import com.example.diplomproject.data.database.MovieEntity

class MovieAdapter(
    private val deleteAction: (MovieEntity) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieEntities = mutableListOf<MovieEntity>()

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.poster)
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val yearTextView: TextView = itemView.findViewById(R.id.year)
        val countryTextView: TextView = itemView.findViewById(R.id.country)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieEntities[position]
        with(holder) {
            nameTextView.text = movie.name
            yearTextView.text = movie.year.toString()
            countryTextView.text = movie.country

            Glide.with(posterImageView)
                .load(movie.posterURL)
                .centerCrop()
                .into(posterImageView)

            deleteButton.setOnClickListener{
                deleteAction(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieEntities.size
    }

    fun reload(data: List<MovieEntity>) {
        val diffUtilCallback = MovieDiffUtilCallback(KpApplication.database.movieDao().getAlphabetizedMovieList(), data)
        val moviesDiffResult = DiffUtil.calculateDiff(diffUtilCallback)
        movieEntities = data.toMutableList()
        moviesDiffResult.dispatchUpdatesTo(this)
    }
}
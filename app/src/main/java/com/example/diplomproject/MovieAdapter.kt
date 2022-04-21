package com.example.diplomproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val deleteAction: (Int) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.poster)
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val yearTextView: TextView = itemView.findViewById(R.id.year)
        val countryTextView: TextView = itemView.findViewById(R.id.country)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_button)
        val watchButton: ImageView = itemView.findViewById(R.id.watch_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        with(holder) {
            nameTextView.text = movie.name
            yearTextView.text = movie.year.toString()
            countryTextView.text = movie.country

            Glide.with(posterImageView)
                .load(movie.posterURL)
                .centerCrop()
                .into(posterImageView)

            deleteButton.setOnClickListener{
                deleteAction(position)
            }

            watchButton.setOnClickListener{

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
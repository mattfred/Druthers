package com.example.druthers.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.druthers.databinding.ItemMovieBinding
import com.example.druthers.models.Movie

/**
 * Adapter for the [RecyclerView] in [ListFragment].
 */
class MovieListAdapter(private var movieList: List<Movie>, private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val listener: (Movie) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.root.setOnClickListener {
                listener(movie)
            }
            binding.title.text = movie.title
            if (movie.poster.contains("http")) {
                Glide.with(binding.root.context)
                    .load(movie.poster)
                    .into(binding.imageView)
            } else {
                Glide.with(binding.root.context)
                    .load("https://via.placeholder.com/300x450.png?text=No+Image")
                    .into(binding.imageView)
            }
            binding.executePendingBindings()
        }
    }
}
package com.curso.addmovies.adapters.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.curso.addmovies.R
import com.curso.demo_retrofit.models.Movie
import com.curso.demo_retrofit.remote.ApiService

class GetSearchMultiAdapter(val onClickSearch: (Movie) -> Unit) :
    RecyclerView.Adapter<GetSearchMultiAdapter.ViewHolder>() {

    var dataMovieSearch = mutableListOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_search_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataMovieSearch[position])
    }

    override fun getItemCount(): Int {
        return dataMovieSearch.size
    }

    fun updateData(moviesData: List<Movie>) {
        dataMovieSearch = moviesData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageMovieSearch = itemView.findViewById<ImageView>(R.id.imageMovieSearch)
        val cardSearchMovie = itemView.findViewById<CardView>(R.id.cardMoviesSearch)
        val titleMovie = itemView.findViewById<TextView>(R.id.TitlemovieSearch)

        fun bind(item: Movie) {

            val urlImagesSearch = ApiService.URL_IMAGES + item.poster_path
            Glide.with(cardSearchMovie).load(urlImagesSearch).into(imageMovieSearch)
            cardSearchMovie.setOnClickListener {
                Log.v("Pulso sobre Search", item.id.toString())
                onClickSearch(item)
            }
        }
    }
}
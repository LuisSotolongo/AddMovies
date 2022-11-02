package com.curso.addmovies.adapters.getmovies

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

class GetPopularAdapter(val onClickPopular: (Movie) -> Unit) :
    RecyclerView.Adapter<GetPopularAdapter.ViewHolder>() {

    var dataMoviePopular = mutableListOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_moviegetpopular, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataMoviePopular[position])
    }

    override fun getItemCount(): Int {
        return dataMoviePopular.size
    }
    fun updateData(moviesData: List<Movie>) {
        dataMoviePopular = moviesData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageMoviePopular = itemView.findViewById<ImageView>(R.id.imageMoviePopular)
        val cardPopular = itemView.findViewById<CardView>(R.id.cardPopular)
        val title = itemView.findViewById<TextView>(R.id.titleMoviePopular)

        fun bind(item: Movie) {
            title.text = item.title
            val urlImagesPopular = ApiService.URL_IMAGES + item.poster_path
            Glide.with(cardPopular).load(urlImagesPopular).into(imageMoviePopular)
            cardPopular.setOnClickListener {
                Log.v("Pulso sobre Popular", item.id.toString())
                onClickPopular(item)
            }

        }

    }
}
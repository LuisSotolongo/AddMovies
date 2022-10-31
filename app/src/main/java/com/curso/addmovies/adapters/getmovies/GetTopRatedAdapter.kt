package com.curso.addmovies.adapters.getmovies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.curso.addmovies.R
import com.curso.demo_retrofit.models.Movie
import com.curso.demo_retrofit.remote.ApiService

class GetTopRatedAdapter (val onClickTopRated: (Movie) -> Unit) :
    RecyclerView.Adapter<GetTopRatedAdapter.ViewHolder>() {

    var dataMovieUpComing = mutableListOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_movietoprated, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataMovieUpComing[position])
    }

    override fun getItemCount(): Int {
        return dataMovieUpComing.size
    }

    fun updateData(moviesData: List<Movie>) {
        dataMovieUpComing = moviesData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageMovieTopRated = itemView.findViewById<ImageView>(R.id.imageTopRated)
        val cardTopRated = itemView.findViewById<CardView>(R.id.cardTopRated)


        fun bind(item: Movie) {

            val urlImagesTopRated = ApiService.URL_IMAGES + item.poster_path
            Glide.with(cardTopRated).load(urlImagesTopRated).into(imageMovieTopRated)
            cardTopRated.setOnClickListener {
                Log.v("Pulso sobre TOP RATED", item.id.toString())
                onClickTopRated(item)
            }
        }
    }
}
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

class GetTrendingAdapter(val onClickTrending: (Movie) -> Unit) :
    RecyclerView.Adapter<GetTrendingAdapter.ViewHolder>() {

    var dataMovieTrending = mutableListOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_moviegettrending, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataMovieTrending[position])
    }

    override fun getItemCount(): Int {
        return dataMovieTrending.size
    }

    fun updateData(moviesData: List<Movie>) {
        dataMovieTrending = moviesData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageMovieTrending = itemView.findViewById<ImageView>(R.id.imageTrending)
        val cardTrending = itemView.findViewById<CardView>(R.id.cardTrending)


        fun bind(item: Movie) {

            val urlImagesTrending = ApiService.URL_IMAGES + item.poster_path
            Glide.with(cardTrending).load(urlImagesTrending).into(imageMovieTrending)
            cardTrending.setOnClickListener {
                Log.v("Pulso sobre Trending", item.id.toString())
                onClickTrending(item)
            }
        }
    }
}
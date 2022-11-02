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

class GetNowPlayingAdapter(val onClickNowPlaying: (Movie) -> Unit) :
    RecyclerView.Adapter<GetNowPlayingAdapter.ViewHolder>() {

    var dataMovieNowPlaying = mutableListOf<Movie>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GetNowPlayingAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_moviegetnowplaying, parent, false)
         return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: GetNowPlayingAdapter.ViewHolder, position: Int) {
        holder.bind(dataMovieNowPlaying[position])
    }

    override fun getItemCount(): Int {
        return dataMovieNowPlaying.size
    }

    fun updateData(moviesData: List<Movie>) {
        dataMovieNowPlaying = moviesData.toMutableList()
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageMovieNowplaying = itemView.findViewById<ImageView>(R.id.imageNowplaying)
        val cardNowPlaying = itemView.findViewById<CardView>(R.id.cardNowPlaying)
        val title = itemView.findViewById<TextView>(R.id.titleMovieNowplaying)

        fun bind(item: Movie) {
            title.text = item.title
            val urlImagesNowPlaying = ApiService.URL_IMAGES + item.poster_path
            Glide.with(cardNowPlaying).load(urlImagesNowPlaying).into(imageMovieNowplaying)
            cardNowPlaying.setOnClickListener {
                Log.v("Pulso sobre Now Playing", item.id.toString())
                onClickNowPlaying(item)
            }
        }

    }

}
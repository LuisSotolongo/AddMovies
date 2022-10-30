package com.curso.addmovies.adapters.movies

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

class DetailMovieAdapter( var data:List<Movie>) :
    RecyclerView.Adapter<DetailMovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_detail_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }




    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val movie = itemView.findViewById<TextView>(R.id.titleDetailMovie)
        val imagemovie = itemView.findViewById<ImageView>(R.id.detailImageMovie)
        val card = itemView.findViewById<CardView>(R.id.cardDetailMovie)


        fun bind(item: Movie) {
            movie.text = item.title

            val urlImages = ApiService.URL_IMAGES + item.poster_path
            Glide.with(card).load(urlImages).into(imagemovie)
            card.setOnClickListener {
                Log.v("Pulso sobre", item.id.toString())

            }
        }


    }
}
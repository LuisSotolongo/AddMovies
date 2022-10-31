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

class GetUpComingAdapter(val onClickUpComing: (Movie) -> Unit) :
    RecyclerView.Adapter<GetUpComingAdapter.ViewHolder>() {

    var dataMovieUpComing = mutableListOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_moviegetupcoming, parent, false)
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

        val imageMovieUpComing = itemView.findViewById<ImageView>(R.id.imageUpcoming)
        val cardUpComing = itemView.findViewById<CardView>(R.id.cardUpComing)


        fun bind(item: Movie) {

            val urlImagesUpComing = ApiService.URL_IMAGES + item.poster_path
            Glide.with(cardUpComing).load(urlImagesUpComing).into(imageMovieUpComing)
            cardUpComing.setOnClickListener {
                Log.v("Pulso sobre UP Coming", item.id.toString())
                onClickUpComing(item)
            }
        }
    }
}
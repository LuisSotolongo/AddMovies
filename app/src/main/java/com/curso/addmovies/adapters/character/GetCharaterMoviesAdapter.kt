package com.curso.addmovies.adapters.character

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
import com.curso.addmovies.models.CastX

import com.curso.demo_retrofit.remote.ApiService

class GetCharacterMoviesAdapter(val onClickCharacterMovie: (CastX) -> Unit) :
    RecyclerView.Adapter<GetCharacterMoviesAdapter.ViewHolder>() {

    var dataCharacterMovie = mutableListOf<CastX>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GetCharacterMoviesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_character_movies, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GetCharacterMoviesAdapter.ViewHolder, position: Int) {
       holder.bind(dataCharacterMovie[position])
    }

    override fun getItemCount(): Int {
        return dataCharacterMovie.size
    }
    fun updateData(CharacterMoviesData: List<CastX>) {
        dataCharacterMovie = CharacterMoviesData.toMutableList()
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageCharacterMovies = itemView.findViewById<ImageView>(R.id.imageMoviePopular)
        val cardCharacterMovies = itemView.findViewById<CardView>(R.id.cardPopular)
        val title = itemView.findViewById<TextView>(R.id.titleMoviePopular)


        fun bind(item: CastX) {
            title.text = item.title
            val urlImagesPopular = ApiService.URL_IMAGES + item.poster_path
            Glide.with(cardCharacterMovies).load(urlImagesPopular).into(imageCharacterMovies)
            cardCharacterMovies.setOnClickListener {
                Log.v("Pulso sobre Popular", item.id.toString())
                onClickCharacterMovie(item)
            }

        }
    }
}
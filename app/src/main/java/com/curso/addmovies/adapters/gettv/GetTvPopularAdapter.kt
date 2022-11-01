package com.curso.addmovies.adapters.gettv

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.curso.addmovies.R
import com.curso.addmovies.models.Tv
import com.curso.demo_retrofit.remote.ApiService

class GetTvPopularAdapter(val onClickTvPopular: (Tv) -> Unit) :
    RecyclerView.Adapter<GetTvPopularAdapter.ViewHolder>() {

    var dataTvPopular = mutableListOf<Tv>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_gettvpopular, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataTvPopular[position])
    }

    override fun getItemCount(): Int {
        return dataTvPopular.size
    }

    fun updateData(moviesData: List<Tv>) {
        dataTvPopular = moviesData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageTvPopular = itemView.findViewById<ImageView>(R.id.imageTvPopular)
        val cardTvPopular = itemView.findViewById<CardView>(R.id.cardtvPopular)


        fun bind(item: Tv) {

            val urlImagesTvPopular = ApiService.URL_IMAGES + item.poster_path
            Glide.with(cardTvPopular).load(urlImagesTvPopular).into(imageTvPopular)
            cardTvPopular.setOnClickListener {
                Log.v("Pulso sobre TV Airing Today", item.id.toString())
                onClickTvPopular(item)
            }
        }
    }


}
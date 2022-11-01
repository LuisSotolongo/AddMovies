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

class GetTvOnAirAdapter (val onClickOnAir: (Tv) -> Unit) :
    RecyclerView.Adapter<GetTvOnAirAdapter.ViewHolder>() {

    var dataTVOnAir = mutableListOf<Tv>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_gettvonair, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataTVOnAir[position])
    }

    override fun getItemCount(): Int {
        return dataTVOnAir.size
    }

    fun updateData(moviesData: List<Tv>) {
        dataTVOnAir = moviesData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageTvOnAir = itemView.findViewById<ImageView>(R.id.imageTvOnAir)
        val cardTvOnAir = itemView.findViewById<CardView>(R.id.cardtvOnAir)


        fun bind(item: Tv) {

            val urlImagesTvOnAir = ApiService.URL_IMAGES + item.poster_path
            Glide.with(cardTvOnAir).load(urlImagesTvOnAir).into(imageTvOnAir)
            cardTvOnAir.setOnClickListener {
                Log.v("Pulso sobre TV Airing Today", item.id.toString())
                onClickOnAir(item)
            }
        }
    }


}
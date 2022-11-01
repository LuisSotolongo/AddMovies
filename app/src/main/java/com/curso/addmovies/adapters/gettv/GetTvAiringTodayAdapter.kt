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

class GetTvAiringTodayAdapter(val onClickAiringToday: (Tv) -> Unit) :
    RecyclerView.Adapter<GetTvAiringTodayAdapter.ViewHolder>() {

    var dataTVAiringToday = mutableListOf<Tv>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_gettvairingtoday, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataTVAiringToday[position])
    }

    override fun getItemCount(): Int {
        return dataTVAiringToday.size
    }

    fun updateData(moviesData: List<Tv>) {
        dataTVAiringToday = moviesData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageTVAiringToday = itemView.findViewById<ImageView>(R.id.imageTvAiringToday)
        val cardTVAiringToday = itemView.findViewById<CardView>(R.id.cardtvAiringToday)


        fun bind(item: Tv) {

            val urlImagesTVAiringToday = ApiService.URL_IMAGES + item.poster_path
            Glide.with(cardTVAiringToday).load(urlImagesTVAiringToday).into(imageTVAiringToday)
            cardTVAiringToday.setOnClickListener {
                Log.v("Pulso sobre TV Airing Today", item.id.toString())
                onClickAiringToday(item)
            }
        }
    }


}
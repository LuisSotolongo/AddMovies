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

class GetTvTopRatedAdapter (val onClickTopRated: (Tv) -> Unit) :
    RecyclerView.Adapter<GetTvTopRatedAdapter.ViewHolder>() {

    var dataTVTopRated = mutableListOf<Tv>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_gettvtoprated, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataTVTopRated[position])
    }

    override fun getItemCount(): Int {
        return dataTVTopRated.size
    }

    fun updateData(moviesData: List<Tv>) {
        dataTVTopRated = moviesData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageTVTopRated = itemView.findViewById<ImageView>(R.id.imageTvTopRated)
        val cardTVTopRated = itemView.findViewById<CardView>(R.id.cardtvTopRated)


        fun bind(item: Tv) {

            val urlImagesTVTopRated = ApiService.URL_IMAGES + item.poster_path
            Glide.with(cardTVTopRated).load(urlImagesTVTopRated).into(imageTVTopRated)
            cardTVTopRated.setOnClickListener {
                Log.v("Pulso sobre TV Airing Today", item.id.toString())
                onClickTopRated(item)
            }
        }
    }


}
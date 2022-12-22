package com.curso.addmovies.adapters.trailers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.curso.addmovies.R
import com.curso.addmovies.models.Trailer

import com.curso.demo_retrofit.remote.ApiService
import com.google.android.material.button.MaterialButtonToggleGroup

class VideoTrailerAdapter(val onClickTrailer: (Trailer) -> Unit) :
RecyclerView.Adapter<VideoTrailerAdapter.ViewHolder>() {

    var dataTrailer = mutableListOf<Trailer>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_trailers, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataTrailer[position])
    }

    override fun getItemCount(): Int {
        return dataTrailer.size
    }

    fun updateData(moviesData: List<Trailer>) {
        dataTrailer = moviesData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val videoTrailer = itemView.findViewById<ImageView>(R.id.videoTRailers)
        val cardTrailers = itemView.findViewById<CardView>(R.id.cardTrailer)
        val titleTrailers = itemView.findViewById<TextView>(R.id.titleTrailers)
        val botonTrailer = itemView.findViewById<MaterialButtonToggleGroup>(R.id.playTrailer)

        fun bind(item: Trailer) {
            titleTrailers.text = item.name
            val urlItrailers = ApiService.Video_Trailer + item.key

            }
        }
    }

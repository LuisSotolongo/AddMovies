package com.curso.addmovies.adapters.character

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.curso.addmovies.R
import com.curso.addmovies.adapters.getmovies.GetTopRatedAdapter
import com.curso.addmovies.models.Cast
import com.curso.demo_retrofit.models.Movie
import com.curso.demo_retrofit.remote.ApiService

class GetCharacterAdapter(val onClickCast: (Cast) -> Unit) :
    RecyclerView.Adapter<GetCharacterAdapter.ViewHolder>() {

    var dataCast = mutableListOf<Cast>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_characters, parent, false)
  return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: GetCharacterAdapter.ViewHolder, position: Int) {
        holder.bind(dataCast[position])

    }

    override fun getItemCount(): Int {
        return dataCast.size
    }
    fun updateData(moviesCast: List<Cast>) {
        dataCast = moviesCast.toMutableList()
        Log.v("DATACAST", "$dataCast")
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageCast = itemView.findViewById<ImageView>(R.id.imageCredits)
        val cardCast = itemView.findViewById<CardView>(R.id.cardCredits)


        fun bind(item: Cast) {

            val urlImagesCast = ApiService.URL_IMAGES + item.profile_path

            Log.v("urlImagesCast", "$urlImagesCast")
            Glide.with(cardCast).load(urlImagesCast).transform(CircleCrop()).into(imageCast)
            cardCast.setOnClickListener {
                Log.v("Pulso sobre CAST", item.id.toString())
                onClickCast(item)
            }
        }

    }

}
package com.curso.addmovies.views.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.curso.addmovies.DataHolder
import com.curso.addmovies.DataHolder.idMovie
import com.curso.addmovies.DataHolder.idTv
import com.curso.addmovies.R
import com.curso.addmovies.adapters.character.GetCharacterAdapter
import com.curso.addmovies.models.Actor
import com.curso.addmovies.models.Tv
import com.curso.addmovies.views.characters.GetCharacterTvViewModel
import com.curso.addmovies.views.characters.GetCharacterViewModel
import com.curso.demo_retrofit.remote.ApiService
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch


class DetailsTvFragment : Fragment() {

    private val viewModelTv: DetailTvViewModel by activityViewModels()
    private val viewModelTVCast: GetCharacterTvViewModel by activityViewModels()
    private lateinit var adapterCast: GetCharacterAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarTv = view.findViewById<MaterialToolbar>(R.id.toolbarDetailTv)


        val navHostFragment = NavHostFragment.findNavController(this);
        NavigationUI.setupWithNavController(toolbarTv, navHostFragment)
        toolbarTv.title = "Tv Detail"


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {


                launch {
                    viewModelTv.tvDetail.collect {
                        pintarDatosTv(
                            listOf<Tv>(
                                Tv(it.backdrop_path, it.first_air_date, null,it.id,it.name,
                                    null,null, it.original_name, it.overview,
                                    it.popularity, it.poster_path, it.vote_average, it.vote_count

                                )
                            )
                        )


                    }
                }
                launch {
                    viewModelTVCast.tvCast.collect{
                        adapterCast.updateData(it.cast)
                    }
                }

            }

        }
        adapterCast = GetCharacterAdapter {

            DataHolder.idMovie = it.id!!
            Log.v("ID Character DETAILS TV", "${it.id}")
            findNavController().navigate(R.id.action_detailsTvFragment_to_detailCharacterFragment)

        }
        val moviesRecyclerCast = view.findViewById<RecyclerView>(R.id.castTv_recyclerview)
        moviesRecyclerCast.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerCast.adapter = adapterCast


        viewModelTv.getTv(idTv.toString())
        viewModelTVCast.getTvCast(idTv.toString())
Log.v("TVDETAILS", "${idTv.toString()}")
    }
    private fun pintarDatosTv(datosTv: List<Tv>) {

        val titleTv = view?.findViewById<TextView>(R.id.titleDetailTv)
        val imageTv = view?.findViewById<ImageView>(R.id.backgroundImageTv)
        val overViewTv = view?.findViewById<TextView>(R.id.overviewTv)
        val posterTv = view?.findViewById<ImageView>(R.id.detailImageTv)
val cardTV = view?.findViewById<CardView>(R.id.cardDetailTv)
        for (i in datosTv){
            if (titleTv != null) {
                titleTv.text = i.name
            }
            if (overViewTv != null) {
                overViewTv.text = i.overview
            }


            val urlImagesTv = ApiService.URL_IMAGES + i.backdrop_path
            if (cardTV != null) {
                if (imageTv != null) {
                    Glide.with(cardTV).load(urlImagesTv).into(imageTv)
                }
            }
            val urlImagesposterTv = ApiService.URL_IMAGES + i.poster_path
            if (cardTV != null) {
                if (posterTv != null) {
                    Glide.with(cardTV).load(urlImagesposterTv).into(posterTv)
                }
            }
        }
    }
}
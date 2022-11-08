package com.curso.addmovies.views.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
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
import com.curso.addmovies.R
import com.curso.addmovies.adapters.character.GetCharacterAdapter
import com.curso.addmovies.views.characters.GetCharacterViewModel
import com.curso.demo_retrofit.models.Movie
import com.curso.demo_retrofit.remote.ApiService
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch


class DetailsMovieFragment : Fragment() {
    private val viewModel: DetailMovieViewModel by activityViewModels()
    private val viewModelCast: GetCharacterViewModel by activityViewModels()

    private lateinit var adapterCast: GetCharacterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbarDetailMovie)


        val navHostFragment = NavHostFragment.findNavController(this);
        NavigationUI.setupWithNavController(toolbar, navHostFragment)
        toolbar.title = "Movie Detail"

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {


                launch {
                    viewModel.movieDetail.collect {
                        pintarDatos(
                            listOf<Movie>(
                                Movie(
                                    null, it.backdrop_path,
                                    null, it.id, null,
                                    null, it.overview, it.popularity,
                                    it.poster_path, it.release_date, it.title,
                                    null, it.vote_average, it.vote_count
                                )
                            )
                        )


                    }
                }
                launch {
                    viewModelCast.movieCast.collect {
                        adapterCast.updateData(it.cast)
                    }
                }

            }

        }

        adapterCast = GetCharacterAdapter {

            DataHolder.idMovie = it.id!!
            Log.v("ID Castigador", "${it.id}")
            findNavController().navigate(R.id.action_detailsMovieFragment_to_detailCharacterFragment)

        }


        val moviesRecyclerCast = view.findViewById<RecyclerView>(R.id.cast_recyclerview)
        moviesRecyclerCast.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerCast.adapter = adapterCast


        val idMovie = DataHolder.idMovie


        viewModel.getMovie(idMovie.toString())
        viewModelCast.getCast(idMovie.toString())


    }

    fun pintarDatos(datospeliculas: List<Movie>) {

        for (i in datospeliculas) {


            val movie = view?.findViewById<TextView>(R.id.titleDetailMovie)
            val imagemovie = view?.findViewById<ImageView>(R.id.detailImageMovie)
            val card = view?.findViewById<CardView>(R.id.cardDetailMovie)
            val backgroundImage = view?.findViewById<ImageView>(R.id.backgroundImage)
            val sinopsis = view?.findViewById<TextView>(R.id.overview)
            // val popularidad = view?.findViewById<TextView>(R.id.popularidad)
            val releaseDate = view?.findViewById<TextView>(R.id.releaseDate)
            //val mediaVoto = view?.findViewById<TextView>(R.id.mediaVoto)
            //val votos = view?.findViewById<TextView>(R.id.votos)


            Log.v("TITULO", "${i.title}")

            if (movie != null) {
                movie.text = " ${i.title}"
            }
            if (sinopsis != null) {
                sinopsis.text = "Sinopsis\n ${i.overview}"
            }
            /* if (popularidad != null) {
                 popularidad.text = " Popularidad:\n ${i.popularity.toString()}"
             }*/
            var date = i.release_date
            var dateOk = date?.split('-')?.reversed()?.joinToString('-'.toString())
            if (releaseDate != null) {
                releaseDate.text = "Fecha Estreno: $dateOk"
            }
            /* if (mediaVoto != null) {
                 mediaVoto.text = "Media Votos:\n ${i.vote_average.toString()}"
             }*/
            /*if (votos != null) {
                votos.text = "Votos:\n ${i.vote_count.toString()}"
            }*/

            val urlImages = ApiService.URL_IMAGES + i.poster_path
            if (card != null) {
                if (imagemovie != null) {
                    Glide.with(card).load(urlImages).into(imagemovie)
                }
            }
            val urlImagesback = ApiService.URL_IMAGES + i.backdrop_path
            context?.let {
                if (backgroundImage != null) {
                    Glide.with(it).load(urlImagesback).into(backgroundImage)
                }
            }
        }

    }
}
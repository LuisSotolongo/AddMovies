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
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.curso.addmovies.DataHolder

import com.curso.addmovies.R
import com.curso.addmovies.adapters.character.GetCharacterAdapter
import com.curso.addmovies.adapters.character.GetCharacterMoviesAdapter

import com.curso.addmovies.models.Actor
import com.curso.addmovies.views.characters.GetCharacterViewModel
import com.curso.addmovies.views.movies.GetCharacterMoviesViewModel
import com.curso.demo_retrofit.remote.ApiService
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch


class DetailCharacterFragment : Fragment() {
    private val viewModelCharacter: DetailCharacterViewModel by activityViewModels()
    private val viewModelActor: DetailCharacterViewModel by activityViewModels()
    private val viewModelCharacterMovies: GetCharacterMoviesViewModel by activityViewModels()
    private val viewModelCast: GetCharacterViewModel by activityViewModels()

    private lateinit var adapterCast: GetCharacterAdapter
   private lateinit var adapterCharacterMovies: GetCharacterMoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_character, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarCharacter = view.findViewById<MaterialToolbar>(R.id.toolbarDetailCharacter)

        val navHostFragment = NavHostFragment.findNavController(this);
        NavigationUI.setupWithNavController(toolbarCharacter, navHostFragment)

        toolbarCharacter.title = "Actor Detail"

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {


                launch {
                    viewModelCharacter.movieCharacter.collect {
                        pintarDatosActor(
                            listOf<Actor>(
                                Actor(
                                    null, null, it.biography, it.birthday, it.deathday,
                                    null, null, it.id, null, null,
                                    it.name, it.place_of_birth, null, it.profile_path

                                )
                            )
                        )


                    }
                }
                launch {
                    viewModelCharacterMovies.characterMovies.collect {
                        adapterCharacterMovies.updateData(it.cast)
                    }
                }

            }

        }
        adapterCharacterMovies = GetCharacterMoviesAdapter {
            DataHolder.idMovie = it.id!!

            Log.v("ID MOVIE", "${it.id}")
           // findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }

        val moviesRecyclerCharacter = view.findViewById<RecyclerView>(R.id.movies_character_recyclerview)
        moviesRecyclerCharacter.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerCharacter.adapter = adapterCharacterMovies

        val idMovie = DataHolder.idMovie
        viewModelActor.getActorDetail(idMovie.toString())
        viewModelCharacterMovies.getCharacMovies(idMovie.toString())
        Log.v("detail Character id", "${idMovie.toString()}")
    }

    private fun pintarDatosActor(datosActor: List<Actor>) {

        val nameActor = view?.findViewById<TextView>(R.id.nameCharacter)
        val imagenActor = view?.findViewById<ImageView>(R.id.detailImageCharacter)
        val birthayActor = view?.findViewById<TextView>(R.id.birthday)
        val biographyActor = view?.findViewById<TextView>(R.id.biography)
        val popularityActor = view?.findViewById<TextView>(R.id.popularity)

        val cardActor = view?.findViewById<CardView>(R.id.cardDetailCharacter)

        for (i in datosActor) {

            nameActor?.let {
                nameActor.text = i.name
            }
            /*if (nameActor != null) {
                nameActor.text = i.name
            }*/
            if (biographyActor != null) {
                biographyActor.text = i.biography
            }
            var birthday = i.birthday
            var dateOk = birthday?.split('-')?.reversed()?.joinToString('-'.toString())

            if (birthayActor != null) {
                birthayActor.text = "Fecha de Nacimiento $dateOk"
            }


            val urlImages = ApiService.URL_IMAGES + i.profile_path
            if (cardActor != null) {
                if (imagenActor != null) {
                    Glide.with(cardActor).load(urlImages).into(imagenActor)
                }
            }
        }
    }
}
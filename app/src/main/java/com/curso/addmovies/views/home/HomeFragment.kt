package com.curso.addmovies.views.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curso.addmovies.DataHolder
import com.curso.addmovies.R
import com.curso.addmovies.adapters.getmovies.GetNowPlayingAdapter
import com.curso.addmovies.adapters.getmovies.GetTrendingAdapter
import com.curso.addmovies.adapters.getmovies.gettv.GetTvAiringTodayAdapter
import com.curso.addmovies.adapters.search.GetSearchMultiAdapter
import com.curso.addmovies.views.getmovies.GetMoviesNowPlayingViewModel
import com.curso.addmovies.views.getmovies.GetMoviesTrendingViewModel
import com.curso.addmovies.views.gettv.GetTvAiringTodayViewModel
import com.curso.addmovies.views.search.GetMultiSearchViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val viewModelTrending: GetMoviesTrendingViewModel by activityViewModels()
    private val viewmodelSearch: GetMultiSearchViewModel by activityViewModels()
    private val viewModelNowPaying: GetMoviesNowPlayingViewModel by activityViewModels()

    private val viewModelTvAiringToday: GetTvAiringTodayViewModel by activityViewModels()

    private lateinit var progressBar: ProgressBar
    private lateinit var adapterTrending: GetTrendingAdapter
    private lateinit var adapterTvAiringToday: GetTvAiringTodayAdapter
    private lateinit var adapterNowPlaying: GetNowPlayingAdapter
    private lateinit var adapterSearch: GetSearchMultiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        progressBar = view.findViewById(R.id.loading_spinner_home)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModelTrending.loading.collect {
                        if (it) {
                            progressBar.visibility = View.VISIBLE
                        } else {
                            progressBar.visibility = View.GONE
                        }
                    }
                }
                launch {
                    viewModelTrending.getMoviesListTrending.collect {
                        adapterTrending.updateData(it.results)

                    }
                }

                launch {
                    viewModelNowPaying.getMoviesListNowPlaying.collect {
                        adapterNowPlaying.updateData(it.results)
                    }
                }
                launch {
                    viewModelTvAiringToday.getTvListAiringToday.collect {
                        adapterTvAiringToday.updateData(it.results)

                    }
                }

            }
        }
        adapterTrending = GetTrendingAdapter {

            DataHolder.idMovie = it.id!!
            Log.v("ID TRENDING", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }


        adapterNowPlaying = GetNowPlayingAdapter {
            DataHolder.idMovie = it.id!!
            Log.v("ID NOW PLAYING", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }

        adapterTvAiringToday = GetTvAiringTodayAdapter {

            DataHolder.idTv = it.id!!
            Log.v("ID TV AIRING TODAY", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsTvFragment)

        }

//Pelicula del dia
        val moviesRecyclerTrending = view.findViewById<RecyclerView>(R.id.trending_recyclerview)
        moviesRecyclerTrending.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerTrending.adapter = adapterTrending
//en cartelera
        val moviesRecyclerNowPlaying = view.findViewById<RecyclerView>(R.id.nowplaying_recyclerview)
        moviesRecyclerNowPlaying.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerNowPlaying.adapter = adapterNowPlaying
        val moviesRecyclerAiringToday =
            view.findViewById<RecyclerView>(R.id.tvairingtoday_recyclerview)
        moviesRecyclerAiringToday.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerAiringToday.adapter = adapterTvAiringToday


      viewModelNowPaying.getMoviesNowPlaying()
        viewModelTvAiringToday.getTvsAiringToday()
        viewModelTrending.getMoviesTrending()


    }


}



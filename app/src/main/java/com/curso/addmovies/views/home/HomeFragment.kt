package com.curso.addmovies.views.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
import com.curso.addmovies.adapters.getmovies.*
import com.curso.addmovies.views.getmovies.*
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val viewModelTrending: GetMoviesTrendingViewModel by activityViewModels()
    private val viewModelPopular: GetMoviesPopularViewModel by activityViewModels()
    private val viewModelNowPaying: GetMoviesNowPlayingViewModel by activityViewModels()
    private val viewModelTopRated: GetMoviesTopRatedViewModel by activityViewModels()
    private val viewModelUpComing: GetMoviesUpcomingViewModel by activityViewModels()


    private lateinit var progressBar: ProgressBar
    private lateinit var adapterTrending: GetTrendingAdapter
    private lateinit var adapterPopular: GetPopularAdapter
    private lateinit var adapterUpComing: GetUpComingAdapter
    private lateinit var adapterTopRated: GetTopRatedAdapter
    private lateinit var adapterNowPlaying: GetNowPlayingAdapter


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
                    viewModelPopular.getMoviesListPopular.collect {
                        adapterPopular.updateData(it.results)
                    }
                }
                launch {
                    viewModelNowPaying.getMoviesListNowPlaying.collect {
                        adapterNowPlaying.updateData(it.results)
                    }
                }
                launch {
                    viewModelTopRated.getMoviesListTopRated.collect {
                        adapterTopRated.updateData(it.results)
                    }
                }
                launch {
                    viewModelUpComing.getMoviesListUpcoming.collect {
                        adapterUpComing.updateData(it.results)
                    }
                }
            }
        }
        adapterTrending = GetTrendingAdapter {

            DataHolder.idMovie = it.id!!
            Log.v("ID TRENDING", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }
        adapterPopular = GetPopularAdapter {
            DataHolder.idMovie = it.id!!
            Log.v("ID POPULAR", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }
        adapterUpComing = GetUpComingAdapter {
            DataHolder.idMovie = it.id!!
            Log.v("ID UP COMING", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }
        adapterNowPlaying = GetNowPlayingAdapter {
            DataHolder.idMovie = it.id!!
            Log.v("ID NOW PLAYING", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }
        adapterTopRated = GetTopRatedAdapter {
            DataHolder.idMovie = it.id!!
            Log.v("ID TOP RATED", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }





        val moviesRecyclerPopular = view.findViewById<RecyclerView>(R.id.popular_recyclerview)
        moviesRecyclerPopular.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerPopular.adapter = adapterPopular

        val moviesRecyclerTrending = view.findViewById<RecyclerView>(R.id.trending_recyclerview)
        moviesRecyclerTrending.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerTrending.adapter = adapterTrending

        val moviesRecyclerNowPlaying = view.findViewById<RecyclerView>(R.id.nowplaying_recyclerview)
        moviesRecyclerNowPlaying.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerNowPlaying.adapter = adapterNowPlaying

        val moviesRecyclerTopRated = view.findViewById<RecyclerView>(R.id.toprated_recyclerview)
        moviesRecyclerTopRated.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerTopRated.adapter = adapterTopRated

        val moviesRecyclerUpComming = view.findViewById<RecyclerView>(R.id.upcomming_recyclerview)
        moviesRecyclerUpComming.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerUpComming.adapter = adapterUpComing

        viewModelNowPaying.getMoviesNowPlaying()
        viewModelPopular.getMoviesPopular()
        viewModelTrending.getMoviesTrending()
        viewModelTopRated.getMoviesTopRated()
        viewModelUpComing.getMoviesUpcoming()


    }


}
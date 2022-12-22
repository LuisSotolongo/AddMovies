package com.curso.addmovies.views.tv

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
import com.curso.addmovies.adapters.getmovies.gettv.GetTvAiringTodayAdapter
import com.curso.addmovies.adapters.getmovies.gettv.GetTvOnAirAdapter
import com.curso.addmovies.adapters.getmovies.gettv.GetTvPopularAdapter
import com.curso.addmovies.adapters.getmovies.gettv.GetTvTopRatedAdapter

import com.curso.addmovies.views.gettv.*
import kotlinx.coroutines.launch


class TvFragment : Fragment() {
    private val viewModelTvOnAir: GetTvOnAirViewModel by activityViewModels()
    private val viewModelTvAiringToday: GetTvAiringTodayViewModel by activityViewModels()
    private val viewModelTvPopular: GetTvPopularViewModel by activityViewModels()

    private val viewModelTvTopRated: GetTvTopRatedViewModel by activityViewModels()

    private lateinit var progressBar: ProgressBar
    private lateinit var adapterTvOnAir: GetTvOnAirAdapter
    private lateinit var adapterTvAiringToday: GetTvAiringTodayAdapter
    private lateinit var adapterTvPopular: GetTvPopularAdapter

    private lateinit var adapterTvTopRated: GetTvTopRatedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.loading_spinner_tv)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModelTvOnAir.loading.collect {
                        if (it) {
                            progressBar.visibility = View.VISIBLE
                        } else {
                            progressBar.visibility = View.GONE
                        }
                    }
                }
                launch {
                    viewModelTvAiringToday.getTvListAiringToday.collect {
                        adapterTvAiringToday.updateData(it.results)

                    }
                }
                launch {
                    viewModelTvPopular.getTvListPopular.collect {
                        adapterTvPopular.updateData(it.results)
                    }
                }

                launch {
                    viewModelTvOnAir.getTvListOnAir.collect {
                        adapterTvOnAir.updateData(it.results)
                    }
                }
                launch {
                    viewModelTvTopRated.getTvListTopRated.collect {
                        adapterTvTopRated.updateData(it.results)
                    }
                }
            }
        }
        adapterTvAiringToday = GetTvAiringTodayAdapter {

            DataHolder.idTv = it.id!!
            Log.v("ID TV AIRING TODAY", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsTvFragment)

        }
        adapterTvPopular = GetTvPopularAdapter {
            DataHolder.idTv = it.id!!
            Log.v("ID TV POPULAR", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsTvFragment)

        }

        adapterTvOnAir = GetTvOnAirAdapter {
            DataHolder.idTv = it.id!!
            Log.v("ID TV ON AIR", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsTvFragment)

        }
        adapterTvTopRated = GetTvTopRatedAdapter {
            DataHolder.idTv = it.id!!
            Log.v("ID TV TOP RATED", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsTvFragment)

        }




//popular
        val tvRecyclerPopular = view.findViewById<RecyclerView>(R.id.tvpopular_recyclerview)
        tvRecyclerPopular.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        tvRecyclerPopular.adapter = adapterTvPopular


//en cartelera
        val tvRecyclerOnAir = view.findViewById<RecyclerView>(R.id.tvonair_recyclerview)
        tvRecyclerOnAir.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        tvRecyclerOnAir.adapter = adapterTvOnAir
//mejor valoradas
        val tvRecyclerTopRated = view.findViewById<RecyclerView>(R.id.tvtoprated_recyclerview)
        tvRecyclerTopRated.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        tvRecyclerTopRated.adapter = adapterTvTopRated
//proximamente
        val moviesRecyclerAiringToday = view.findViewById<RecyclerView>(R.id.tvairingtoday_recyclerview)
        moviesRecyclerAiringToday.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerAiringToday.adapter = adapterTvAiringToday

        viewModelTvTopRated.getTvsTopRated()
        viewModelTvPopular.getTvsPopular()
        viewModelTvAiringToday.getTvsAiringToday()

        viewModelTvOnAir.getTvsOnAir()


    }


}
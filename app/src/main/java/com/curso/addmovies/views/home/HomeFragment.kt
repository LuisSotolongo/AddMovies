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
import com.curso.addmovies.adapters.getmovies.GetTrendingAdapter
import com.curso.addmovies.views.getmovies.GetMoviesViewModel
import com.curso.demo_retrofit.models.Movies
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private val viewModel: GetMoviesViewModel by activityViewModels()

    private lateinit var progressBar: ProgressBar
    private lateinit var adapterTrending: GetTrendingAdapter


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
                    viewModel.loading.collect {
                        if (it) {
                            progressBar.visibility = View.VISIBLE
                        } else {
                            progressBar.visibility = View.GONE
                        }
                    }
                }
                launch {
                    viewModel.getMoviesListTrending.collect {
                        adapterTrending.updateData(it.results)
                    }
                }


            }

        }
        adapterTrending = GetTrendingAdapter {
            DataHolder.idMovie = it.id!!
            Log.v("CULO", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }

        val moviesRecylcergettrending =
            view.findViewById<RecyclerView>(R.id.moviesRecylcergettrending)
        moviesRecylcergettrending.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
          moviesRecylcergettrending.adapter = adapterTrending
        viewModel.getMoviesTrending()
    }
}
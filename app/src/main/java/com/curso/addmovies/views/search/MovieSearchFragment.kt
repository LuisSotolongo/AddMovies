package com.curso.addmovies.views.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curso.addmovies.DataHolder
import com.curso.addmovies.DataHolder.name
import com.curso.addmovies.R
import com.curso.addmovies.adapters.getmovies.GetNowPlayingAdapter
import com.curso.addmovies.adapters.getmovies.GetTrendingAdapter
import com.curso.addmovies.adapters.gettv.GetTvAiringTodayAdapter
import com.curso.addmovies.adapters.search.GetSearchMultiAdapter
import kotlinx.coroutines.launch


class MovieSearchFragment : Fragment() {
    private val viewmodelSearch: GetMultiSearchViewModel by activityViewModels()
    private lateinit var progressBar: ProgressBar

    private lateinit var adapterSearch: GetSearchMultiAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.loading_spinner_search)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewmodelSearch.loading.collect {
                        if (it) {
                            progressBar.visibility = View.VISIBLE
                        } else {
                            progressBar.visibility = View.GONE
                        }
                    }
                }
                launch {
                    viewmodelSearch.getListSearch.collect {
                        adapterSearch.updateData(it.results)

                    }
                }



            }
        }
        adapterSearch = GetSearchMultiAdapter {

            DataHolder.name = it.title!!
            Log.v("ID SEARCH", "${it.id}")
            findNavController().navigate(R.id.action_movieSearchFragment_to_detailsMovieFragment)

        }





        val moviesRecyclerSearch = view.findViewById<RecyclerView>(R.id.searchRecycler)

        moviesRecyclerSearch.adapter = adapterSearch



        viewmodelSearch.getSearchList(name)


    }

}
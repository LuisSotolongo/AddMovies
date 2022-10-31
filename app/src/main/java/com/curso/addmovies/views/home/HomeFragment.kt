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
import com.curso.addmovies.adapters.recycler.MainRecyclerAdapter
import com.curso.addmovies.models.AllCategory
import com.curso.addmovies.views.getmovies.GetMoviesPopularViewModel
import com.curso.addmovies.views.getmovies.GetMoviesTrendingViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var mainCategoryRecycler:RecyclerView? = null
    private var mainRecyclerAdapter: MainRecyclerAdapter? = null
    private val viewModelTrending: GetMoviesTrendingViewModel by activityViewModels()
    private val viewModelPopular: GetMoviesPopularViewModel by activityViewModels()
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
            Log.v("ID POPULAR", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }
        adapterNowPlaying = GetNowPlayingAdapter {
            DataHolder.idMovie = it.id!!
            Log.v("ID POPULAR", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }
        adapterTopRated = GetTopRatedAdapter {
            DataHolder.idMovie = it.id!!
            Log.v("ID POPULAR", "${it.id}")
            findNavController().navigate(R.id.action_containerFragment_to_detailsMovieFragment)

        }





        /*val moviesRecylcergettrending =
            view.findViewById<RecyclerView>(R.id.moviesRecylcergettrending)
        moviesRecylcergettrending.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecylcergettrending.adapter = adapterTrending
        viewModelTrending.getMoviesTrending()


        val moviesRecyclerPopular = view.findViewById<RecyclerView>(R.id.moviesRecylcergetPopular)
        moviesRecyclerPopular.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerPopular.adapter = adapterPopular*/
        /*val allCategory: MutableList<AllCategory> = ArrayList()
        allCategory.add(AllCategory("Popular", categoryitemList))
        allCategory.add(AllCategory("Trending", categoryitemList2))
        allCategory.add(AllCategory("Now Playing", categoryitemList3))
        allCategory.add(AllCategory("Top Rated", categoryitemList4))
        allCategory.add(AllCategory("Pelicula del dia",categoryitemList5))*/

    }
    private fun setmainCategoryRecycler(allCategory: List<AllCategory>){

        mainCategoryRecycler = view?.findViewById(R.id.parent_recyclerview)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
        mainCategoryRecycler!!.layoutManager= layoutManager
        mainRecyclerAdapter = this.context?.let { MainRecyclerAdapter(it,allCategory) }
        mainCategoryRecycler!!.adapter = mainRecyclerAdapter
    }
}
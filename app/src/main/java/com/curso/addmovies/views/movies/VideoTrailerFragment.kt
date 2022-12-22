package com.curso.addmovies.views.movies

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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curso.addmovies.DataHolder
import com.curso.addmovies.R
import com.curso.addmovies.adapters.trailers.VideoTrailerAdapter
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch


class VideoTrailerFragment : Fragment() {

    private val viewModelTrailer: VideoTrailerViewModel by activityViewModels()


    private lateinit var adapterTrailer: VideoTrailerAdapter

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_trailer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarCharacter = view.findViewById<MaterialToolbar>(R.id.toolbarTrailer)

        val navHostFragment = NavHostFragment.findNavController(this);
        NavigationUI.setupWithNavController(toolbarCharacter, navHostFragment)

        toolbarCharacter.title = "Trailers"

        progressBar = view.findViewById(R.id.loading_spinner_trailers)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModelTrailer.loading.collect {
                        if (it) {
                            progressBar.visibility = View.VISIBLE
                        } else {
                            progressBar.visibility = View.GONE
                        }
                    }
                }
                launch {
                    viewModelTrailer.getTrailerList.collect {
                        adapterTrailer.updateData(it.trailers)
                    }
                }

                adapterTrailer = VideoTrailerAdapter {
                    DataHolder.name = it.name!!
                    Log.v("ID TRAILER", "${it.name}")
                }
            }
        }



        val trailersRecyclerView = view.findViewById<RecyclerView>(R.id.trailers_recyclerview)
        trailersRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        trailersRecyclerView.adapter = adapterTrailer
        val name = DataHolder.name
        viewModelTrailer.getTrailer(name.toString())
    }
}
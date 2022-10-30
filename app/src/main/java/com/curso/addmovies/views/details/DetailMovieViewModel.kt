package com.curso.addmovies.views.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.demo_retrofit.models.Movie
import com.curso.demo_retrofit.models.Movies
import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailMovieViewModel: ViewModel() {


val movieDetail = MutableStateFlow(Movie())
val loading = MutableStateFlow(false)

    fun getMovie(id: String) {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getMovieDetail(id_movie = id)
            if (response.isSuccessful) {
                movieDetail.value = response.body() ?: Movie()


                Log.v(
                    "MoviesDetailVM",
                    "Todo fenomenal en la petición de movies detail ${response.toString()}"
                )
                Log.v(
                    "MoviesDetailVM",
                    "Todo fenomenal en la petición de movies detail ${movieDetail.value}"
                )
            } else {
                Log.v("Genres", "Error en la petición de generos ${response.toString()}")
            }
            loading.value = false
        }

        val ldl = listOf<Movie>(
            Movie()
        )


        Log.v("MoviesMutable","$movieDetail" )


    }
}
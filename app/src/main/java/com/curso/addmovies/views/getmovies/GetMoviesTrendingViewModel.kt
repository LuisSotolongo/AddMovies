package com.curso.addmovies.views.getmovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.demo_retrofit.models.Movies
import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetMoviesTrendingViewModel: ViewModel() {
    val getMoviesListTrending = MutableStateFlow(Movies())
    val loading = MutableStateFlow(false)


    fun getMoviesTrending() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getMoviesTrending()
            if (response.isSuccessful) {
                getMoviesListTrending.value = response.body() ?: Movies()
                Log.v("PELICULAS Trending", "Todo fenomenal en la petición de generos ${getMoviesListTrending.value}")
            } else {
                Log.v("Trending", "Error en la petición de generos ${response.toString()}")
            }
            loading.value = false
        }
    }




}
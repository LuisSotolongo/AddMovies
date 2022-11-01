package com.curso.addmovies.views.getmovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.demo_retrofit.models.Movies
import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetMoviesNowPlayingViewModel: ViewModel() {
    val getMoviesListNowPlaying = MutableStateFlow(Movies())
    val loading = MutableStateFlow(false)

    fun getMoviesNowPlaying() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getMoviesNowPlaying()
            if (response.isSuccessful) {
                getMoviesListNowPlaying.value = response.body() ?: Movies()
                Log.v("PELICULAS NowPlaying", "Todo fenomenal en la petición de generos ${getMoviesListNowPlaying.value}")
            } else {
                Log.v("NowPlaying", "Error en la petición de generos ${response.toString()}")
            }
            loading.value = false
        }
    }
}
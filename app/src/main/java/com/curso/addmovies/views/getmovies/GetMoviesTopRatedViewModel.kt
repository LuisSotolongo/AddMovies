package com.curso.addmovies.views.getmovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.demo_retrofit.models.Movies
import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetMoviesTopRatedViewModel: ViewModel() {

    val getMoviesListTopRated = MutableStateFlow(Movies())
    val loading = MutableStateFlow(false)

    fun getMoviesTopRated() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getMoviesTopRated()
            if (response.isSuccessful) {
                getMoviesListTopRated.value = response.body() ?: Movies()
                Log.v("PELICULAS TopRated", "Todo fenomenal en la petición de generos ${getMoviesListTopRated.value}")
            } else {
                Log.v("TopRated", "Error en la petición de generos ${response.toString()}")
            }
            loading.value = false
        }
    }


}
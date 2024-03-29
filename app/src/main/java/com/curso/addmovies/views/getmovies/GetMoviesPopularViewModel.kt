package com.curso.addmovies.views.getmovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.demo_retrofit.models.Movies
import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetMoviesPopularViewModel: ViewModel() {

    val getMoviesListPopular = MutableStateFlow(Movies())
    val loading = MutableStateFlow(false)

    fun getMoviesPopular() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getMoviesPopular()
            if (response.isSuccessful) {
                getMoviesListPopular.value = response.body() ?: Movies()
                Log.v("PELICULAS Popular", "Todo fenomenal en la petición de generos ${getMoviesListPopular.value}")
            } else {
                Log.v("Popular", "Error en la petición de generos ${response.toString()}")
            }
            loading.value = false
        }
    }


}
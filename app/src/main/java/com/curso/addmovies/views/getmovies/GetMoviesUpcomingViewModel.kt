package com.curso.addmovies.views.getmovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.demo_retrofit.models.Movies
import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetMoviesUpcomingViewModel: ViewModel() {


    val getMoviesListUpcoming = MutableStateFlow(Movies())
    val loading = MutableStateFlow(false)

    fun getMoviesUpcoming() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getMoviesUpcoming()
            if (response.isSuccessful) {
                getMoviesListUpcoming.value = response.body() ?: Movies()
                Log.v("PELICULAS Upcoming", "Todo fenomenal en la petición de generos ${getMoviesListUpcoming.value}")
            } else {
                Log.v("Upcoming", "Error en la petición de generos ${response.toString()}")
            }
            loading.value = false
        }
    }




}
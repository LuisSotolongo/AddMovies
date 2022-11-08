package com.curso.addmovies.views.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.addmovies.models.ActorsMovies

import com.curso.demo_retrofit.models.Movies
import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetCharacterMoviesViewModel : ViewModel() {

    val characterMovies = MutableStateFlow(ActorsMovies())
    val loading = MutableStateFlow(false)


    fun getCharacMovies(id: String) {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getCharactersMovies(personId = id)
            if (response.isSuccessful) {
                characterMovies.value = response.body() ?: ActorsMovies()
                Log.v(
                    "Character Movies",
                    "Todo fenomenal en la petición de Character Movies ${characterMovies.value} ${response.toString()}"
                )
            } else {
                Log.v(
                    "Character Movies",
                    "Error en la petición de Character Movies ${response.toString()}"
                )
            }
            loading.value = false
        }
    }


}
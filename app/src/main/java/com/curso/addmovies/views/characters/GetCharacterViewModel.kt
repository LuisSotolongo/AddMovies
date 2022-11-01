package com.curso.addmovies.views.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.addmovies.models.Cast
import com.curso.addmovies.models.Characters

import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetCharacterViewModel: ViewModel() {


    val movieCast = MutableStateFlow(Characters())
    val loading = MutableStateFlow(false)


    fun getCast(id: String) {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getMovieCredits(id_movie = id)
            if (response.isSuccessful) {
                movieCast.value = response.body() ?: Characters()


                Log.v(
                    "Cast DEtails",
                    "Todo fenomenal en la petición de movies detail ${response.toString()}"
                )
                Log.v(
                    "Cast DEtails",
                    "Todo fenomenal en la petición de movies detail ${movieCast.value}"
                )
            } else {
                Log.v("Cast DEtails", "Error en la petición de generos ${response.toString()}")
            }
            loading.value = false
        }


        Log.v("CASTMutable","$movieCast" )


    }

}
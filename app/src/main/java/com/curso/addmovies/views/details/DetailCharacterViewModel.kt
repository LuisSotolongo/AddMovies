package com.curso.addmovies.views.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.addmovies.models.Actor

import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailCharacterViewModel: ViewModel() {

    val movieCharacter = MutableStateFlow(Actor())
    val loading = MutableStateFlow(false)

    fun getActorDetail(idActor: String) {
        Log.v("ID Actor VIEW MODEL", "$idActor")
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getActorDetails(personId = idActor)
            if (response.isSuccessful) {
                movieCharacter.value = response.body() ?: Actor()


                Log.v(
                    "DetailActor",
                    "Todo fenomenal en la petición de movies detail ${response.toString()}"
                )
                Log.v(
                    "DetailActor",
                    "Todo fenomenal en la petición de movies detail ${movieCharacter.value}"
                )
            } else {
                Log.v("DetailActor ", "Error en la petición de generos ${response.toString()}")
            }
            loading.value = false
        }



        Log.v("MoviesMutable","$movieCharacter" )


    }
}
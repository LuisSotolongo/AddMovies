package com.curso.addmovies.views.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.addmovies.models.Trailers

import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class VideoTrailerViewModel: ViewModel() {

    val getTrailerList = MutableStateFlow(Trailers())
    val loading = MutableStateFlow(false)

    fun getTrailer(id: String) {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getTrailers(idmovie = id)
            if (response.isSuccessful) {
                getTrailerList.value = response.body() ?: Trailers()


                Log.v(
                    "TRailers",
                    "Todo fenomenal en la petición de TRailers ${response.toString()}"
                )
                Log.v(
                    "TRailers",
                    "Todo fenomenal en la petición de TRailers ${getTrailerList.value}"
                )
            } else {
                Log.v("Detail TRailers ViewModel", "Error en la petición de TRailers ${response.toString()}")
            }
            loading.value = false
        }




        Log.v("TRailersMutable","$getTrailerList" )


    }
}
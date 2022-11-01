package com.curso.addmovies.views.gettv

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.addmovies.models.Tvs
import com.curso.demo_retrofit.models.Movies
import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetTvOnAirViewModel: ViewModel() {

    val getTvListOnAir = MutableStateFlow(Tvs())
    val loading = MutableStateFlow(false)

    fun getTvsOnAir() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getTvsOnAir()
            if (response.isSuccessful) {
                getTvListOnAir.value = response.body() ?: Tvs()
                Log.v("TV", "Todo fenomenal en la petición de TV ON AIR ${getTvListOnAir.value}")
            } else {
                Log.v("TV", "Error en la petición de TV ${response.toString()}")
            }
            loading.value = false
        }
    }
}
package com.curso.addmovies.views.gettv

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.addmovies.models.Tvs

import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetTvTopRatedViewModel: ViewModel() {


    val getTvListTopRated = MutableStateFlow(Tvs())
    val loading = MutableStateFlow(false)

    fun getTvsTopRated() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getTvsTopRated()
            if (response.isSuccessful) {
                getTvListTopRated.value = response.body() ?: Tvs()
                Log.v("TV", "Todo fenomenal en la petición de TV TOP RATED ${getTvListTopRated.value} $response")

            } else {
                Log.v("TV", "Error en la petición de TV ${response.toString()}")
            }
            loading.value = false
        }
    }


}
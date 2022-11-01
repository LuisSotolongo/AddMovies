package com.curso.addmovies.views.gettv

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.addmovies.models.Tvs

import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetTvAiringTodayViewModel:ViewModel() {

    val getTvListAiringToday = MutableStateFlow(Tvs())
    val loading = MutableStateFlow(false)

    fun getTvsAiringToday() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getTvsAiringToday()
            if (response.isSuccessful) {
                getTvListAiringToday.value = response.body() ?: Tvs()
                Log.v("TV", "Todo fenomenal en la petición de TV AIRING TODAY ${getTvListAiringToday.value}")
            } else {
                Log.v("TV", "Error en la petición de TV ${response.toString()}")
            }
            loading.value = false
        }
    }



}
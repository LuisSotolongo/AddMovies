package com.curso.addmovies.views.gettv

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.addmovies.models.Tvs
import com.curso.demo_retrofit.models.Movies
import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetTvPopularViewModel:ViewModel() {


    val getTvListPopular = MutableStateFlow(Tvs())
    val loading = MutableStateFlow(false)

    fun getTvsPopular() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getTvsPopular()
            if (response.isSuccessful) {
                getTvListPopular.value = response.body() ?: Tvs()
                Log.v("TV", "Todo fenomenal en la petición de TV POPULAR ${getTvListPopular.value}")
            } else {
                Log.v("TV", "Error en la petición de TV ${response.toString()}")
            }
            loading.value = false
        }
    }



}
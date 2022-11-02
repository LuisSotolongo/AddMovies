package com.curso.addmovies.views.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.addmovies.models.Tv

import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailTvViewModel: ViewModel() {

    val tvDetail = MutableStateFlow(Tv())
    val loading = MutableStateFlow(false)

    fun getTv(id: String) {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getTvDetails(tvId = id)
            if (response.isSuccessful) {
                tvDetail.value = response.body() ?: Tv()


                Log.v(
                    "TVDetailVM",
                    "Todo fenomenal en la petición de TV detail ${response.toString()}"
                )
                Log.v(
                    "TVDetailVM",
                    "Todo fenomenal en la petición de TV detail ${tvDetail.value}"
                )
            } else {
                Log.v("Detail Movie ViewModel", "Error en la petición de generos ${response.toString()}")
            }
            loading.value = false
        }




        Log.v("tvMutable","$tvDetail" )


    }


}
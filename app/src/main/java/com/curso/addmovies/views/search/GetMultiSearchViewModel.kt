package com.curso.addmovies.views.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.curso.demo_retrofit.models.Movies
import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetMultiSearchViewModel: ViewModel() {

    val getListSearch = MutableStateFlow(Movies())
    val loading = MutableStateFlow(false)

    fun getSearchList(name: String) {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getDbSearch(querysearch = name)
            if (response.isSuccessful) {
                getListSearch.value = response.body() ?: Movies()
                Log.v("TV", "Todo fenomenal en la petición de SEARCH ${getListSearch.value}")
            } else {
                Log.v("TV", "Error en la petición de SEARCH ${response.toString()}")
            }
            loading.value = false
        }
    }

}
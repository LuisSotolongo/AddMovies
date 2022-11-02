package com.curso.addmovies.views.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.addmovies.models.Characters
import com.curso.demo_retrofit.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetCharacterTvViewModel: ViewModel() {

    val tvCast = MutableStateFlow(Characters())
    val loading = MutableStateFlow(false)


    fun getTvCast(id: String) {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getTvCredits(id_tv = id)
            if (response.isSuccessful) {
                tvCast.value = response.body() ?: Characters()


                Log.v(
                    "Cast DEtails",
                    "Todo fenomenal en la petición de movies detail ${response.toString()}"
                )
                Log.v(
                    "Cast DEtails",
                    "Todo fenomenal en la petición de movies detail ${tvCast.value}"
                )
            } else {
                Log.v("Cast DEtails", "Error en la petición de generos ${response.toString()}")
            }
            loading.value = false
        }


        Log.v("CASTMutable","$tvCast" )


    }

}




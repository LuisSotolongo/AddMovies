package com.curso.demo_retrofit.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiService {


    lateinit var api:Api

    var URL = "https://api.themoviedb.org/3/"
    val api_key = "061b44b74471c164f4baecc2c453eb91"
    val language = "es-ES"
    val language1 = "en-US"
    val URL_IMAGES = "https://image.tmdb.org/t/p/w500/"


    init {
        initService()
    }


    private fun initService(){

        val retroifit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retroifit.create(Api::class.java)
    }
}
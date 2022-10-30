package com.curso.demo_retrofit.remote

import com.curso.demo_retrofit.models.Genres
import com.curso.demo_retrofit.models.Movie
import com.curso.demo_retrofit.models.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language
    ): Response<Genres>

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
        @Query("with_genres") genres: String
    ): Response<Movies>

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") movie_id: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Movie>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id_movie: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Movie>

    @GET("trending/movie/day?")
    suspend fun getMoviesTrending(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Movies>

    @GET("movie/popular?")
    suspend fun getMoviesPopular(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Movies>

    @GET("movie/now_playing?")
    suspend fun getMoviesNowPlaying(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Movies>

    @GET("movie/upcoming?")
    suspend fun getMoviesUpcoming(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Movies>


    @GET(" movie/top_rated?")
    suspend fun getMoviesTopRated(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Movies>

    @GET("/search/movie?")
    suspend fun getMovieSearch(
        @Path("movie_id") id_movie: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Movies>

    @GET("movie/{movie_id}/credits?")
    suspend fun getMovieCredits(
        @Path("movie_id") id_movie: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Movies>
}
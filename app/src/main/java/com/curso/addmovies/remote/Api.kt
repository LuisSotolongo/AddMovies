package com.curso.demo_retrofit.remote

import com.curso.addmovies.models.*
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

    @GET("search/multi")
    suspend fun getDbSearch(
        @Path("query") querysearch: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Movies>

    @GET("movie/{movie_id}/credits?")
    suspend fun getMovieCredits(
        @Path("movie_id") id_movie: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Characters>

    @GET("tv/{tv_id}/credits?")
    suspend fun getTvCredits(
        @Path("tv_id") id_tv: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Characters>


    @GET("tv/popular?")
    suspend fun getTvsPopular(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Tvs>

    @GET("tv/airing_today?")
    suspend fun getTvsAiringToday(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Tvs>

    @GET("tv/on_the_air?")
    suspend fun getTvsOnAir(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Tvs>


    @GET("tv/top_rated?")
    suspend fun getTvsTopRated(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Tvs>

    @GET("person/{person_id}")
    suspend fun getActorDetails(
        @Path("person_id") personId: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language1,
    ): Response<Actor>

    @GET("tv/{tv_id}")
    suspend fun getTvDetails(
        @Path("tv_id") tvId: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Tv>


    @GET("person/{person_id}/movie_credits?")
    suspend fun getCharactersMovies(
        @Path("person_id") personId: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<ActorsMovies>

    @GET("movie/{movie_id}/videos?")
    suspend fun getTrailers(
        @Path("movie_id") idmovie: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Trailers>

}
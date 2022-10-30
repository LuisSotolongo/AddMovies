package com.curso.addmovies.models

data class Characters(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf(),
    val id: Int? = null
)
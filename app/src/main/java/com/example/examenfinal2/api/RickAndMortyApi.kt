package com.example.examenfinal2.api

import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    suspend fun getAllCharacters(): RickAndMortyResponse
}
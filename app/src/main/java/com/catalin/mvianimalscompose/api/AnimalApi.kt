package com.catalin.mvianimalscompose.api

import com.catalin.mvianimalscompose.model.Animal
import retrofit2.http.GET

interface AnimalApi {

    @GET("animals.json")
    suspend fun getAnimals(): List<Animal>

}
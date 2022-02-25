package com.catalin.mvianimalscompose.api

class AnimalRepo(private val api: AnimalApi) {
    suspend fun getAnimals() = api.getAnimals()
}
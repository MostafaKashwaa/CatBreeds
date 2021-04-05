package com.kashwaa.shared.repositories

import com.kashwaa.shared.domain.Breed

interface BreedRepo {
    suspend fun getAllBreeds(): List<Breed>
    suspend fun getBreedsPage(page: Int): List<Breed>
    suspend fun getBreed(id: String): Breed
    suspend fun searchBreeds(searchTerm: String): List<Breed>
}
package com.kashwaa.shared.repositories

import com.kashwaa.shared.domain.Breed
import com.kashwaa.shared.netwok.TheCatApi
import com.kashwaa.shared.netwok.toDomain

class BreedRepoRemote: BreedRepo {
    val api = TheCatApi()
    override suspend fun getAllBreeds(): List<Breed> {
        return api.getAllBreeds().toDomain()
    }

    override suspend fun getBreedsPage(page: Int): List<Breed> {
        return api.getBreedsPage(page).toDomain()
    }

    override suspend fun getBreed(id: String): Breed {
        TODO("Not yet implemented")
    }

    override suspend fun searchBreeds(searchTerm: String): List<Breed> {
        TODO("Not yet implemented")
    }

}
package com.kashwaa.shared.repositories

import com.kashwaa.shared.cache.DatabaseDriverFactory
import com.kashwaa.shared.cache.TheCatDB
import com.kashwaa.shared.cache.entityFromDomain
import com.kashwaa.shared.cache.toDomain
import com.kashwaa.shared.domain.Breed
import com.kashwaa.shared.netwok.TheCatApi
import com.kashwaa.shared.netwok.toDomain

class BreedSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val db = TheCatDB(databaseDriverFactory)
    private val api = TheCatApi()

    @Throws(Exception::class)
    suspend fun getBreeds(forceReload: Boolean) : List<Breed> {
        val cachedBreeds = db.query.getAll().executeAsList()
        return if (cachedBreeds.isNotEmpty() && !forceReload) {
            cachedBreeds.toDomain()
        } else {
            api.getAllBreeds().toDomain().also { breeds ->
                db.query.clear()
                breeds.forEach { breed ->
                    db.query.insert(entityFromDomain(breed))
                }
            }
        }
    }
}
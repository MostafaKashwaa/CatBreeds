package com.kashwaa.shared.repositories

import com.kashwaa.shared.cache.DatabaseDriverFactory
import com.kashwaa.shared.cache.TheCatDB
import com.kashwaa.shared.domain.Breed
import com.kashwaa.shared.netwok.TheCatApi

class BreedSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val db = TheCatDB(databaseDriverFactory)
    private val api = TheCatApi()

    @Throws(Exception::class)
    suspend fun getBreeds(forceReload: Boolean) : List<Breed> {
        val cachedBreeds = db.getAllBreeds()
        return if (cachedBreeds.isNotEmpty() && !forceReload) {
            cachedBreeds
        } else {
            api.getAllBreeds().also { breeds ->
                db.clear()
                breeds.forEach { breed ->
                    db.insert(breed)
                }
            }
        }
    }
}
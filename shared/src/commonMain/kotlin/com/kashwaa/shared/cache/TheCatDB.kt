package com.kashwaa.shared.cache

import com.kashwaa.shared.domain.Breed

class TheCatDB(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(
        databaseDriverFactory.createDriver(),
        BreedEntity.Adapter(
            weightAdapter = rangeAdapter,
            temperamentAdapter = listOfStringAdapter,
            lifeSpanAdapter = rangeAdapter,
            altNamesAdapter = listOfStringAdapter
        )
    )
    val query = database.appDatabaseQueries

    suspend fun getAllBreeds(): List<Breed> {
        return query.getAll().executeAsList().toDomain()
    }

    suspend fun insert(breed: Breed) {
        query.insert(entityFromDomain(breed))
    }

    suspend fun clear() {
        query.clear()
    }
}
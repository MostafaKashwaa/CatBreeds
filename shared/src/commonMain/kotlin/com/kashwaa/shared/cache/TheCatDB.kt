package com.kashwaa.shared.cache

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
}
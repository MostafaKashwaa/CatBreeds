package com.kashwaa.shared.netwok

import com.kashwaa.shared.domain.Breed
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get


class TheCatApi {
    private val httpClient by lazy {
        HttpClient {
            install(JsonFeature) {
                val json = Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
                serializer = KotlinxSerializer(json)
            }
        }
    }

    var pageSize = 10

    suspend fun getAllBreeds(): List<Breed> {
        val breeds: List<BreedDTO> = httpClient.get("$BASE_URL/breeds") {}
        return breeds.toDomain()
    }

    suspend fun getBreedsPage(page: Int): List<Breed> {
        val breeds: List<BreedDTO> = httpClient.get("$BASE_URL/breeds?limit=$pageSize&page=$page")
        return breeds.toDomain()
    }

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/v1"
        fun getSampleBreed(): BreedDTO {
            return sampleBreed
        }
    }
}
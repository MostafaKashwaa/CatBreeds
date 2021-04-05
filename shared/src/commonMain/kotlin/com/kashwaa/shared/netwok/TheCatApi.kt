package com.kashwaa.shared.netwok

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

    suspend fun getAllBreeds(): List<BreedDTO> {
        return httpClient.get("$BASE_URL/breeds") {}
    }

    suspend fun getBreedsPage(page: Int): List<BreedDTO> {
        return httpClient.get("$BASE_URL/breeds?limit=$pageSize&page=$page")
    }

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/v1"
        fun getSampleBreed(): BreedDTO {
            return sampleBreed
        }
    }
}
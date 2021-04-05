package com.kashwaa.shared.netwok

import com.kashwaa.shared.domain.Breed
import com.kashwaa.shared.domain.BreedImage
import com.kashwaa.shared.domain.Range
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class BreedDTO(
    val id: String,
    val name: String,
    val description: String,
    val image: BreedImageDTO? = null,
    val weight: WeightDTO? = null,
    val adaptability: Int? = null,
    val temperament: String? = null,
    @SerialName("life_span")
    val lifeSpan: String? = null,
    val origin: String? = null,
    val hypoallergenic: Int? = null,
    val experimental: Int? = null,
    val rare: Int? = null,
    val indoor: Int? = null,
    val natural: Int? = null,
    @SerialName("short_legs")
    val shortLegs: Int? = null,
    val hairless: Int? = null,
    @SerialName("suppressed_tail")
    val suppressedTail: Int? = null,
    val lap: Int? = null,
    @SerialName("affection_level")
    val affectionLevel: Int? = null,
    @SerialName("child_friendly")
    val childFriendly: Int? = null,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int? = null,
    @SerialName("dog_friendly")
    val dogFriendly: Int? = null,
    @SerialName("energy_level")
    val energyLevel: Int? = null,
    val grooming: Int? = null,
    val intelligence: Int? = null,
    @SerialName("health_issues")
    val healthIssues: Int? = null,
    @SerialName("social_needs")
    val socialNeeds: Int? = null,
    val vocalisation: Int? = null,
    @SerialName("shedding_level")
    val hairShedding: Int? = null,
    @SerialName("alt_names")
    val altNames: String? = null,
    @SerialName("wikipedia_url")
    val wikipediaUrl: String? = null,
    @SerialName("cfa_url")
    val cfaUrl: String? = null,
    @SerialName("vcahospitals_url")
    val vcaUrl: String? = null,
    @SerialName("vetstreet_url")
    val vetStreetUrl: String? = null
) {
    fun toJson(): String {
        return Json.encodeToString(this)
    }

    fun toDomain(unitSystem: UnitSystem = UnitSystem.Metric): Breed {
        val lifeSpanValues = this.lifeSpan?.split('-')?.map { it.trim().toIntOrNull()}
        val weightValues =  when(unitSystem) {
            UnitSystem.Metric -> this.lifeSpan?.split('-')?.map { it.trim().toIntOrNull()}
            UnitSystem.Imperial -> this.lifeSpan?.split('-')?.map { it.trim().toIntOrNull()}
        }
        return Breed(
            id = this.id,
            name = this.name,
            description = this.description,
            weight = Range(weightValues?.get(0), weightValues?.get(1)),
            adaptability = this.adaptability,
            temperament = this.temperament?.let { it.split(',') }.orEmpty(),
            lifeSpan = Range(lifeSpanValues?.get(0), lifeSpanValues?.get(1)),
            origin = this.origin,
            hypoallergenic = this.hypoallergenic,
            experimental = this.experimental != 0,
            rare = this.rare != 0,
            indoor = this.indoor != 0,
            natural = this.natural != 0,
            shortLegs = this.shortLegs != 0,
            hairless = this.hairless != 0,
            suppressedTail = this.suppressedTail != 0,
            lap = this.lap,
            affectionLevel = this.affectionLevel,
            childFriendly = this.childFriendly,
            strangerFriendly = this.strangerFriendly,
            dogFriendly = this.dogFriendly,
            energyLevel = this.energyLevel,
            grooming = this.grooming,
            intelligence = this.intelligence,
            healthIssues = this.healthIssues,
            socialNeeds = this.socialNeeds,
            vocalisation = this.vocalisation,
            hairShedding = this.hairShedding,
            altNames = this.altNames?.split(','),
            wikipediaUrl = this.wikipediaUrl,
            cfaUrl = this.cfaUrl,
            vcaUrl = this.vcaUrl,
            vetStreetUrl = this.vetStreetUrl,
            image = this.image?.toDomain()
        )
    }

    sealed class UnitSystem(val weightUnit: String, val lengthUnit: String) {
        object Metric: UnitSystem("kg", "cm")
        object Imperial: UnitSystem("lb", "\"")
    }
}

inline fun <reified T> fromJson(jsonString: String): T {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString<T>(jsonString)
}

@Serializable
data class BreedImageDTO(
    val id: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val url: String? = null
) {
    fun toDomain(): BreedImage {
        return BreedImage(
            id = this.id,
            width = this.width,
            height = this.height,
            url = this.url
        )
    }
}

@Serializable
data class WeightDTO(
    val imperial: String? = null,
    val metric: String? = null
)

fun List<BreedDTO>.toDomain(): List<Breed> {
    return this.map { it.toDomain() }
}

val sampleBreedJson = """
   {
     "adaptability": 5,
     "affection_level": 5,
     "alt_names": "",
     "cfa_url": "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
     "child_friendly": 3,
     "country_code": "EG",
     "country_codes": "EG",
     "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
     "dog_friendly": 4,
     "energy_level": 5,
     "experimental": 0,
     "grooming": 1,
     "hairless": 0,
     "health_issues": 2,
     "hypoallergenic": 0,
     "id": "abys",
     "image": {
       "height": 1445,
       "id": "0XYvRd7oD",
       "url": "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
       "width": 1204
     },
     "indoor": 0,
     "intelligence": 5,
     "lap": 1,
     "life_span": "14 - 15",
     "name": "Abyssinian",
     "natural": 1,
     "origin": "Egypt",
     "rare": 0,
     "reference_image_id": "0XYvRd7oD",
     "rex": 0,
     "shedding_level": 2,
     "short_legs": 0,
     "social_needs": 5,
     "stranger_friendly": 5,
     "suppressed_tail": 0,
     "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
     "vcahospitals_url": "https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
     "vetstreet_url": "http://www.vetstreet.com/cats/abyssinian",
     "vocalisation": 1,
     "weight": {
       "imperial": "7  -  10",
       "metric": "3 - 5"
     },
     "wikipedia_url": "https://en.wikipedia.org/wiki/Abyssinian_(cat)"
   }
    """.trimIndent()
val sampleBreed = fromJson<BreedDTO>(sampleBreedJson)

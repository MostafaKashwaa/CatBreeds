package com.kashwaa.shared.domain

data class Breed(
    val id: String,
    val name: String,
    val description: String,
    val weight: Range<Int?>? = null,
    val adaptability: Int? = null,
    val temperament: List<String> = listOf(),
    val lifeSpan: Range<Int?>? = null,
    val origin: String? = null,
    val hypoallergenic: Int? = null,
    val experimental: Boolean? = null,
    val rare: Boolean? = null,
    val indoor: Boolean? = null,
    val natural: Boolean? = null,
    val shortLegs: Boolean? = null,
    val hairless: Boolean? = null,
    val suppressedTail: Boolean? = null,
    val lap: Int? = null,
    val affectionLevel: Int? = null,
    val childFriendly: Int? = null,
    val strangerFriendly: Int? = null,
    val dogFriendly: Int? = null,
    val energyLevel: Int? = null,
    val grooming: Int? = null,
    val intelligence: Int? = null,
    val healthIssues: Int? = null,
    val socialNeeds: Int? = null,
    val vocalisation: Int? = null,
    val hairShedding: Int? = null,
    val altNames: List<String>? = null,
    val wikipediaUrl: String? = null,
    val cfaUrl: String? = null,
    val vcaUrl: String? = null,
    val vetStreetUrl: String? = null,
    val image: BreedImage? = null
)

data class Range<T: Number?>(val min: T, val max: T) {
    override fun toString(): String {
        return "$min - $max"
    }
}

data class BreedImage(val id: String?, val width: Int?, val height: Int?, val url: String?)
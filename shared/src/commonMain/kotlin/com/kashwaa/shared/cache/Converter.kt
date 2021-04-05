package com.kashwaa.shared.cache

import com.kashwaa.shared.domain.Breed
import com.kashwaa.shared.domain.BreedImage
import com.kashwaa.shared.domain.Range

fun BreedEntity.toDomain(): Breed {
    return Breed(
        id = this.id,
        name = this.name,
        description = this.description,
        weight = Range(this.weight?.min, this.weight?.max),
        adaptability = this.adaptability,
        temperament = this.temperament ?: listOf(),
        lifeSpan = Range(lifeSpan?.min, lifeSpan?.max),
        origin = this.origin,
        hypoallergenic = this.hypoallergenic,
        experimental = this.experimental,
        rare = this.rare,
        indoor = this.indoor,
        natural = this.natural,
        shortLegs = this.shortLegs,
        hairless = this.hairless,
        suppressedTail = this.suppressedTail,
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
        altNames = this.altNames,
        wikipediaUrl = this.wikipediaUrl,
        cfaUrl = this.cfaUrl,
        vcaUrl = this.vcaUrl,
        vetStreetUrl = this.vetStreetUrl,
        image = BreedImage("", 0, 0, this.image_url)
    )
}

fun entityFromDomain(breed: Breed): BreedEntity {
    return BreedEntity(
        id = breed.id,
        name = breed.name,
        description = breed.description,
        weight = Range(breed.weight?.min ?: -1, breed.weight?.max ?: -1),
        adaptability = breed.adaptability,
        temperament = breed.temperament,
        lifeSpan = Range(breed.lifeSpan?.min ?: -1, breed.lifeSpan?.max ?: -1),
        origin = breed.origin,
        hypoallergenic = breed.hypoallergenic,
        experimental = breed.experimental,
        rare = breed.rare,
        indoor = breed.indoor,
        natural = breed.natural,
        shortLegs = breed.shortLegs,
        hairless = breed.hairless,
        suppressedTail = breed.suppressedTail,
        lap = breed.lap,
        affectionLevel = breed.affectionLevel,
        childFriendly = breed.childFriendly,
        strangerFriendly = breed.strangerFriendly,
        dogFriendly = breed.dogFriendly,
        energyLevel = breed.energyLevel,
        grooming = breed.grooming,
        intelligence = breed.intelligence,
        healthIssues = breed.healthIssues,
        socialNeeds = breed.socialNeeds,
        vocalisation = breed.vocalisation,
        hairShedding = breed.hairShedding,
        altNames = breed.altNames,
        wikipediaUrl = breed.wikipediaUrl,
        cfaUrl = breed.cfaUrl,
        vcaUrl = breed.vcaUrl,
        vetStreetUrl = breed.vetStreetUrl,
        image_url = breed.image?.url
    )
}

fun List<BreedEntity>.toDomain() : List<Breed> {
    return this.map { it.toDomain() }
}

fun List<Breed>.toEntityList() : List<BreedEntity> {
    return this.map {
        entityFromDomain(it)
    }
}

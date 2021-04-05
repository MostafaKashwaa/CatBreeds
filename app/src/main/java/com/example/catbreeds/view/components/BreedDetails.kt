package com.example.catbreeds.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil.transform.CircleCropTransformation
import com.kashwaa.shared.domain.Breed
import com.kashwaa.shared.netwok.BreedDTO
import com.kashwaa.shared.netwok.fromJson
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun BreedDetailsScreen(
    breed: Breed
) {
    var openPreview by remember { mutableStateOf(false) }
    Box(Modifier.fillMaxSize()) {
        if (openPreview) {
            breed.image?.url?.let {
                ImagePreviewDialog(imageUrl = it) {
                    openPreview = false
                }
            }
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
                .clickable {
                    openPreview = false
                }
        ) {
            breed.image?.url?.let { url ->
                CoilImage(
                    data = url,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .height(250.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            openPreview = true
                        },
                    requestBuilder = {
                        transformations(CircleCropTransformation())
                    }
                ) {
                    CircularProgressIndicator()
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = breed.name,
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                breed.origin?.let { Text(text = it) }
                breed.lifeSpan?.let { Text(text = it.toString()) }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = breed.description,
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Justify
            )
        }
    }
}


@Composable
fun ImagePreviewDialog(
    imageUrl: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        buttons = {},
        text = {
//            ZoomableImage(imageUrl = imageUrl)
            PreviewBox(imageUrl = imageUrl)
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        backgroundColor = Color.Transparent,
        modifier = Modifier.border(1.dp, Color.White, MaterialTheme.shapes.medium)
    )

}

@Composable
fun ZoomableImage(
    imageUrl: String
) {
    var scale by remember { mutableStateOf(1f) }
    var translate by remember { mutableStateOf(Offset(0f, 0f)) }
    BoxWithConstraints {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.70f)
                .background(Color.Transparent)
                .pointerInput(Unit) {
                    detectTransformGestures { centroid, pan, zoom, rotation ->
                        scale = when {
                            scale < 0.5f -> 0.5f
                            scale > 3f -> 3f
                            else -> scale * zoom
                        }
                        val leftLimit = -maxWidth.value * scale
                        val rightLimit = maxWidth.value * scale
                        val topLimit = -maxHeight.value * scale * 0.6f
                        val bottomLimit = maxHeight.value * scale * 0.6f

                        val xPosition = when {
                            translate.x < leftLimit -> leftLimit
                            translate.x > rightLimit -> rightLimit
                            else -> translate.x + pan.x
                        }
                        val yPosition = when {
                            translate.y < topLimit -> topLimit
                            translate.y > bottomLimit -> bottomLimit
                            else -> translate.y + pan.y
                        }
                        translate = Offset(xPosition, yPosition)
                    }
                }
        ) {
            CoilImage(
                data = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.Center)
                    .graphicsLayer(
                        scaleX = maxOf(0.5f, minOf(3f, scale)),
                        scaleY = maxOf(0.5f, minOf(3f, scale)),
                        translationX = translate.x,
                        translationY = translate.y,

                        )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                scale = when {
                                    scale > 2f -> 1f
                                    else -> 3f
                                }
                                translate = Offset(0f, 0f)
                            }
                        )
                    }
            ) {
                CircularProgressIndicator()
            }
        }
    }

}

@Preview
@Composable
fun BreedDetailsPreview() {
    BreedDetailsScreen(breed = getSampleBreed().toDomain())
}

@Composable
fun getSampleBreed(): BreedDTO {
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
    return fromJson<BreedDTO>(sampleBreedJson)
}
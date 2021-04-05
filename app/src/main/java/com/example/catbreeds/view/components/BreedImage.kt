package com.example.catbreeds.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.example.catbreeds.R
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun BreedImage(imageUrl: String, selected: Boolean, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        if (imageUrl.isBlank()) {
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "default cat image",
                modifier = Modifier
                    .border(
                        width = if (selected) 3.dp else 0.dp,
                        color = MaterialTheme.colors.secondaryVariant,
                        shape = RoundedCornerShape(50)
                    )
                    .clip(RoundedCornerShape(50))
                    .height(65.dp)
            )
        } else {
            CoilImage(
                data = imageUrl,
                contentScale = ContentScale.Inside,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .height(65.dp)
                    .border(
                        width = if (selected) 5.dp else 0.dp,
                        color = MaterialTheme.colors.secondaryVariant,
                        shape = RoundedCornerShape(50)
                    ),
                requestBuilder = {
                    transformations(CircleCropTransformation())
                },
                loading = {
                    Box(Modifier.matchParentSize()) {
                        CircularProgressIndicator(
                            Modifier.align(Alignment.Center),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colors.secondaryVariant
                        )
                    }
                }
            )
        }
    }
}
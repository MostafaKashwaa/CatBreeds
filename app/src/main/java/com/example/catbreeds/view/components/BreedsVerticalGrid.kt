package com.example.catbreeds.view.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.kashwaa.shared.domain.Breed
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun BreedsVerticalGrid(
    items: List<Breed>,
    onBreedClick: (Breed) -> Unit,
    columns: Int = 2
) {
    val chunkedList = items.chunked(columns)

    LazyColumn {
        items(chunkedList) {
            Row {
                it.forEachIndexed { index, item ->
                    Log.i("Grid", "BreedsVerticalGrid: ${(index + 1) / columns.toFloat()}")
                    CardColumnItem(
                        imageUrl = item.image?.url,
                        text = item.name,
                        Modifier
                            .fillMaxWidth((index + 1) / columns.toFloat())
                            .height(90.dp)
                            .clickable { onBreedClick(item) },
                        circleCrop = false
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}

@Composable
fun CardColumnItem(
    imageUrl: String?,
    text: String,
    modifier: Modifier = Modifier,
    circleCrop: Boolean = false
) {
    Card(modifier = modifier) {
        Row {
            imageUrl?.let {
                PetImage(imageUrl, circleCrop)
            }
            Text(
                text = text,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
private fun PetImage(imageUrl: String, circleCrop: Boolean) {
    CoilImage(
        data = imageUrl,
        contentDescription = null,
        modifier = Modifier
            .width(90.dp)
            .height(90.dp),
        contentScale = ContentScale.Fit,
        requestBuilder = {
            if (circleCrop) transformations(CircleCropTransformation())
            else transformations(
                RoundedCornersTransformation(
                    topLeft = 16f,
                    bottomLeft = 4f,
                    topRight = 4f,
                    bottomRight = 16f
                )
            )
        }
    ) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

package com.example.catbreeds.view.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PreviewBox(
    imageUrl: String
) {
    BoxWithGestures {
        Log.i("Compose", "PreviewBox: recomposed with scale = $scale & translate = $translate")
        CoilImage(
            data = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
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
                            Log.i("Compose", "PreviewBox: Scale after doubleTab is: $scale")
                        }
                    )
                }
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun BoxWithGestures(
    content: @Composable GesturesBoxScope.() -> Unit
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
            Log.i("Compose", "BoxWithGestures: Box recomposed with scale: $scale")
            val scope = GesturesBoxScope(
                scaleValue = scale,
                translateValue = translate,
                onScaleChange = { scale = it },
                onTranslateChange = { translate = it }
            )
            Log.i("Compose", "BoxWithGestures: scope scale changed to ${scope.scale}")
            scope.content()
        }
    }
}

data class GesturesBoxScope(
    private var scaleValue: Float,
    private var translateValue: Offset,
    private val onScaleChange: (Float) -> Unit,
    private val onTranslateChange: (Offset) -> Unit
) : BoxScope {
    var scale: Float
        get() = scaleValue
        set(value) {
            Log.i("Compose", "Scope: Scale changed to $scale")
            onScaleChange(value)
            scaleValue = value
        }
    var translate: Offset
        get() = translateValue
        set(value) {
            Log.i("Compose", "Scope: translate changed to $translate")
            onTranslateChange(value)
            translateValue = value
        }
}
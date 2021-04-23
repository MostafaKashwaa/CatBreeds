package com.example.catbreeds.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.catbreeds.BreedsViewModel
import com.example.catbreeds.view.components.BreedsVerticalGrid
import com.kashwaa.shared.domain.Breed

@Composable
fun BreedsScreen(
    viewModel: BreedsViewModel,
    onBreedClick: (Breed) -> Unit
) {
    val breeds = viewModel.breeds
    Box(Modifier.fillMaxSize()) {
        when (viewModel.status) {
            BreedsViewModel.Status.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
            BreedsViewModel.Status.Success -> BreedsVerticalGrid(items = breeds, onBreedClick = onBreedClick, 2)
            else -> ErrorPage(retry = viewModel::onLoadBreeds)
        }
    }
}

@Composable
private fun ErrorPage(retry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Failed to connect",
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        )
        Button(
            onClick = retry,
            Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Try Again")
        }
    }
}




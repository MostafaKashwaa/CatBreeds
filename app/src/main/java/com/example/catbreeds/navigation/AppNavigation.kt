/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.catbreeds.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import com.example.catbreeds.BreedsViewModel
import com.example.catbreeds.BreedsViewModelFactory
import com.example.catbreeds.R
import com.example.catbreeds.view.BreedsScreen
import com.example.catbreeds.view.components.BreedDetailsScreen
import com.google.gson.Gson
import com.kashwaa.shared.cache.DatabaseDriverFactory
import com.kashwaa.shared.domain.Breed
import com.kashwaa.shared.repositories.BreedSDK
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("homeScreen", R.string.home_screen)
    object BreedDetails : Screen("breedDetails", R.string.pet_details_screen)
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            val viewModel = viewModel(
                modelClass = BreedsViewModel::class.java,
                factory = BreedsViewModelFactory(BreedSDK(DatabaseDriverFactory(context = LocalContext.current)))
            )
            viewModel.onLoadBreeds()
            BreedsScreen(
                viewModel = viewModel,
                onBreedClick = {
                    navController.navigate(route = "${Screen.BreedDetails.route}/${it.toJson()}") {
                        popUpTo = navController.graph.startDestination
                        launchSingleTop = true
                    }
                },
            )
        }
        composable(
            "${Screen.BreedDetails.route}/{breed}",
        ) {
            it.arguments?.getString("breed")?.let { json ->
                Column(
                    Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    BreedDetailsScreen(breed = Gson().fromJson(json, Breed::class.java))
                }
            }
        }
    }
}

fun Breed.toJson(): String {
    return Gson().toJson(this)
}
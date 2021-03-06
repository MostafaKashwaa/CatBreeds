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
package com.example.catbreeds

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.catbreeds.navigation.AppNavHost
import com.example.catbreeds.navigation.Screen
import com.example.catbreeds.ui.theme.MyTheme
import com.kashwaa.shared.Greeting
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.statusBarsPadding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate: ${Greeting().greeting()}")
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            window.statusBarColor = Color.Black.copy(alpha = 0.4f).toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER)
            ProvideWindowInsets(consumeWindowInsets = true) {
                MyTheme {
                    MyApp()
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navHostController = rememberNavController()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .statusBarsPadding()
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = "Cat Adoption"
                ) { navHostController.navigate(Screen.Home.route) }
            }
        ) {
            AppNavHost(navController = navHostController)
        }
    }
}

@Composable
fun AppTopBar(title: String, onAppBarIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More icon")
            }
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .height(45.dp)
                    .clickable { onAppBarIconClick() }
            )
        }
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

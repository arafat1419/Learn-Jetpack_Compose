package com.arafat1419.simpletextcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class CoreUI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Surface(
        color = Color.DarkGray,
        modifier = Modifier
            .fillMaxSize()

    ) {
        /*Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalColorBar(Color.Magenta)
            HorizontalColorBar(Color.Red)
            HorizontalColorBar(Color.Magenta)
            HorizontalColorBar(Color.Red)
            HorizontalColorBar(Color.Magenta)
        }*/
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ColorSquare(Color.Magenta)
                ColorSquare(Color.Red)
            }
            ColorSquare(Color.Magenta)
            ColorSquare(Color.Red)
            ColorSquare(Color.Magenta)
            ColorSquare(Color.Red)
            ColorSquare(Color.Magenta)
        }

    }
}

@Composable
fun ColorSquare(color: Color) {
    Surface(
        color = color,
        modifier = Modifier
            .height(60.dp)
            .width(60.dp)
    ) {

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreviewCoreUI() {
    MainScreen()
}
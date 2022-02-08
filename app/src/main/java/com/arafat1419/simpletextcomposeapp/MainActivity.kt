package com.arafat1419.simpletextcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingText(name = "world")
        }
    }
}

/*@Composable
fun GreetingText(name: String) {
    Text(text = "Hello $name!",
        modifier = Modifier
            .size(200.dp, 240.dp)
            .padding(24.dp, 15.dp)
            .clickable(onClick = {  }),
        style = MaterialTheme.typography.button,
        fontWeight = FontWeight.SemiBold
    )
}*/

@Composable
fun GreetingText(name: String) {
    Text("Hi there!",
        Modifier
            .border(2.dp, Color.Green)
            .padding(50.dp)
            .border(2.dp, Color.Red)
            .padding(50.dp)
    )
}

/*@Composable
fun GreetingButton() {
    Button(onClick = {
        // Do Something
    }) {
        GreetingText(name = "Button")
    }
}*/

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GreetingText(name = "world")
}
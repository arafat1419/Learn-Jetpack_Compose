package com.arafat1419.simpletextcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arafat1419.simpletextcomposeapp.ui.theme.SimpleTextComposeAppTheme
import com.arafat1419.simpletextcomposeapp.ui.theme.lightGreen

class ProfileCard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleTextComposeAppTheme() {
                MainnScren()
            }
        }
    }
}

@Composable
fun MainnScren() {
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ProfileCards()
        }
    }
}

@Composable
fun AppBar() {
    TopAppBar(
        title = { Text(text = "Messaging application users") },
        navigationIcon = {
            Icon(
                Icons.Default.Home,
                "Icon Home",
                Modifier
                    .padding(horizontal = 12.dp)
            )

        }
    )
}

@Composable
fun ProfileCards() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture()
            ProfileContent()
        }
    }
}

@Composable
fun ProfilePicture() {
    Card(
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.lightGreen),
        modifier = Modifier
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.fotoku),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(72.dp),
            contentScale = ContentScale.Crop
        )
    }

}

@Composable
fun ProfileContent() {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Arafat Maku",
            style = MaterialTheme.typography.h5
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "Active now",
                style = MaterialTheme.typography.body2
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SimpleTextComposeAppTheme() {
        MainnScren()
    }
}
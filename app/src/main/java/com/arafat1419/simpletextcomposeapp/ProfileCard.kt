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
fun MainnScren(users: List<UserProfile> = userProfileList) {
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column() {
                users.forEach {
                    ProfileCards(userProfile = it)
                }
            }
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
fun ProfileCards(userProfile: UserProfile) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
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
            ProfilePicture(userProfile.photo, userProfile.status)
            ProfileContent(userProfile.name, userProfile.status)
        }
    }
}

@Composable
fun ProfilePicture(picture: Int, status: Boolean) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (status) MaterialTheme.colors.lightGreen else Color.Red
        ),
        modifier = Modifier
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = picture),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(72.dp),
            contentScale = ContentScale.Crop
        )
    }

}

@Composable
fun ProfileContent(name: String, status: Boolean) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        CompositionLocalProvider(LocalContentAlpha provides (
                if (status) 1f else ContentAlpha.medium
                )) {
            Text(
                text = name,
                style = MaterialTheme.typography.h5
            )
        }

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = if (status) "Active now" else "Offline",
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
package com.arafat1419.simpletextcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.arafat1419.simpletextcomposeapp.ui.theme.SimpleTextComposeAppTheme
import com.arafat1419.simpletextcomposeapp.ui.theme.lightGreen

class ProfileCard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleTextComposeAppTheme {
                UsersApplication()
            }
        }
    }
}

@Composable
fun UsersApplication(users: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list") {
        composable("users_list") {
            UserListScreen(users, navController)
        }
        composable(
            route = "user_details/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            })
        ) {
            UserProfileDetailsScreen(it.arguments!!.getInt("userId"), navController)
        }
    }
}

@Composable
fun UserListScreen(users: List<UserProfile>, navController: NavController?) {
    Scaffold(topBar = { AppBar("Users List", Icons.Default.Home) {} }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                items(users) { userProfile ->
                    ProfileCards(userProfile = userProfile) {
                        navController?.navigate("user_details/${userProfile.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun UserProfileDetailsScreen(userId: Int, navController: NavController?) {
    val userProfile = userProfileList.first { userProfile -> userId == userProfile.id }
    Scaffold(topBar = {
        AppBar(
            userProfile.name,
            Icons.Default.ArrowBack
        ) {
            navController?.navigateUp()
        }
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProfilePicture(userProfile.photo, userProfile.status, 240.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
            }
        }
    }
}

@Composable
fun AppBar(title: String, icon: ImageVector, iconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            Icon(
                imageVector = icon,
                "Icon $title",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { iconClick.invoke() })
            )

        }
    )
}

@Composable
fun ProfileCards(userProfile: UserProfile, clickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable(onClick = clickAction),
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.photo, userProfile.status, 72.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(picture: String, status: Boolean, imageSize: Dp) {
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
            painter = rememberImagePainter(
                data = picture,
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(imageSize)
        )
        /*Image(
            painter = painterResource(id = picture),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(72.dp),
            contentScale = ContentScale.Crop
        )*/
    }

}

@Composable
fun ProfileContent(name: String, status: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
//            .fillMaxWidth()
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides (
                    if (status) 1f else ContentAlpha.medium
                    )
        ) {
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
fun UserProfileDetailPreview() {
    SimpleTextComposeAppTheme {
        UserProfileDetailsScreen(0, null)
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    SimpleTextComposeAppTheme {
        UserListScreen(userProfileList, null)
    }
}
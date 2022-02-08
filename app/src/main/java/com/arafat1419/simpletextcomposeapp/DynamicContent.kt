package com.arafat1419.simpletextcomposeapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

/*
val namesList: ArrayList<String> = arrayListOf(
    "Arafat",
    "Shelsi"
)
*/

class DynamicContent : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenn()
        }
    }
}

@Composable
fun MainScreenn() {
    val greetingListState = remember {
        mutableStateListOf("Arafat", "Shelsi")
    }
    val newNameStateContent = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        var counter = greetingListState.size
        GreetingList(greetingListState, {
//            counter++
            greetingListState.add(newNameStateContent.value)
        }, {
//            counter--
            Log.d("Remove", newNameStateContent.value)
            if (greetingListState.contains(newNameStateContent.value)) {
                greetingListState.removeAt(greetingListState.indexOf(newNameStateContent.value))
            }
        }, newNameStateContent.value, { newValue ->
            newNameStateContent.value = newValue
        })
    }
}

@Composable
fun GreetingList(names: List<String>, buttonClick: () -> Unit, minesClick: () -> Unit, textFieldValue: String, textFieldUpdate : (newName: String) -> Unit) {
    names.forEach {
        Greeting(name = it)
    }
    Column(
    ) {
        TextField(value = textFieldValue, onValueChange = textFieldUpdate)
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = buttonClick) {
                Text(text = "Add name")
            }
            Button(onClick = minesClick) {
                Text(text = "Remove name")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography.h5
    )
}

@Preview(showBackground = true)
@Composable
fun DynamicContentPreview() {
    MainScreenn()
}
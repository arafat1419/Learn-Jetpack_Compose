package com.arafat1419.simpletextcomposeapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiftUpViewModel: ViewModel() {
    val textFieldState = MutableLiveData("")

    fun onTextChange(newText: String) {
        textFieldState.value = newText
    }
}
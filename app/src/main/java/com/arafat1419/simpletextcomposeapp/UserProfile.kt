package com.arafat1419.simpletextcomposeapp

data class UserProfile(
    val name: String,
    val status: Boolean,
    val photo: Int
)

val userProfileList = arrayListOf<UserProfile>(
    UserProfile("Mohammad", true, R.drawable.fotoku),
    UserProfile("Arafat", true, R.drawable.fotoku),
    UserProfile("Maku", false, R.drawable.fotoku)
)
package com.example.tintaph

data class Event(
    val imageResId: Int,
    val name: String,
    val dateTime: String,
    val description: String,
    var isBookmarked: Boolean
)

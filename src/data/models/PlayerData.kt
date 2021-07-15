package com.mayurg.data.models

data class PlayerData(
    val username: String,
    val isDrawing: Boolean = false,
    var score: Int = 0,
    var rank: Int = 0
)

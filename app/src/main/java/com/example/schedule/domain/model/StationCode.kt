package com.example.schedule.domain.model

data class StationCode(
    val esrCode: String,
    val yandexCode: String,
    val direction: String,
    val stationType: String,
    val title: String,
    val transportType: String
)
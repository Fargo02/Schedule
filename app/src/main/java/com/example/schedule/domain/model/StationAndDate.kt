package com.example.schedule.domain.model

data class StationAndDate(
    val arrival: String,
    val arrival_platform: String?,
    val arrival_terminal: String?,
    val departure: String,
    val departure_platform: String?,
    val departure_terminal: String?,
    val duration: Int,
    val from: Station,
    val to: Station,
    val start_date: String?,
    val stops: String?,
    val date: String
)

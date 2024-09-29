package com.example.schedule.data.db.entity

data class Station(
    val codes: CodesXXX,
    val direction: String,
    val latitude: Double,
    val longitude: Double,
    val station_type: String,
    val title: String,
    val transport_type: String
)
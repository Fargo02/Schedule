package com.example.schedule.domain.model

data class StationInfo(
    val transportType: String?,
    val shortTitle: String?,
    val companyName: String,
    val vehicle: String?,
    val departure: String,
    val duration: String,
    val titleFrom: String?,
    val titleTo: String?,
    val arrival: String,
)

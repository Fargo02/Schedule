package com.example.schedule.data.db.entity

data class Country(
    val codes: Codes,
    val regions: List<Region>,
    val title: String?
)
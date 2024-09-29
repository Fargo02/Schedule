package com.example.schedule.data.db.entity

data class Region(
    val codes: Codes,
    val settlements: List<Settlement>,
    val title: String
)
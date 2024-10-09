package com.example.schedule.data.db.entity

data class Settlement(
    val codes: Codes,
    val stations: List<Station>,
    val title: String?
)
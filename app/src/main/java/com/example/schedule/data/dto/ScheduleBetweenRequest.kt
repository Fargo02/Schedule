package com.example.schedule.data.dto

data class ScheduleBetweenRequest(
    val fromCode: String,
    val toCode: String,
    val date: String,
)
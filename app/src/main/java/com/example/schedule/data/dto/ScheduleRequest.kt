package com.example.schedule.data.dto

data class ScheduleRequest(
    val fromCode: String,
    val toCode: String,
    val date: String,
    val transportTypes: String,
)
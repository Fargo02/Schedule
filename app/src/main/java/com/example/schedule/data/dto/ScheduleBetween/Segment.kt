package com.example.schedule.data.dto.ScheduleBetween

data class Segment(
    val arrival: String,
    val arrival_platform: String,
    val arrival_terminal: String,
    val departure: String,
    val departure_platform: String,
    val departure_terminal: String,
    val duration: Int,
    val from: FromX,
    val has_transfers: Boolean,
    val start_date: String,
    val stops: String,
    val thread: Thread,
    val tickets_info: TicketsInfo,
    val to: ToX
)
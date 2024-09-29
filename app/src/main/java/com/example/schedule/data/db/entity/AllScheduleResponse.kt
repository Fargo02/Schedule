package com.example.schedule.data.db.entity

import com.example.schedule.data.dto.Response

data class AllScheduleResponse(
    val countries: List<Country>
): Response()
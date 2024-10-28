package com.example.schedule.data.dto

import com.example.schedule.data.dto.ScheduleBetween.Pagination
import com.example.schedule.data.dto.ScheduleBetween.Search
import com.example.schedule.data.dto.ScheduleBetween.Segment

data class ScheduleResponse(
    val interval_segments: List<String>,
    val pagination: Pagination,
    val search: Search,
    val segments: List<Segment>
): Response()
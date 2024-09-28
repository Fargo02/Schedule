package com.example.schedule.domain.api

import com.example.schedule.domain.model.StationAndDate
import com.example.schedule.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ScheduleBetweenRepository {
    fun getScheduleByStationCodeAndDate(
        fromCode: String,
        toCode: String,
        date: String,)
    : Flow<Resource<List<StationAndDate>>>
}
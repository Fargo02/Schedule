package com.example.schedule.domain.api

import com.example.schedule.domain.model.StationInfo
import kotlinx.coroutines.flow.Flow

interface ScheduleInteractor {
    fun getScheduleByStationCodeAndDate(
        fromCode: String,
        toCode: String,
        date: String,
        transportTypes: String)
    : Flow<Pair<List<StationInfo>?, String?>>
}
package com.example.schedule.domain.api

import com.example.schedule.domain.model.StationInfo
import kotlinx.coroutines.flow.Flow

interface ScheduleBetweenInteractor {
    fun getScheduleByStationCodeAndDate(
        fromCode: String,
        toCode: String,
        date: String, )
    : Flow<Pair<List<StationInfo>?, String?>>
}
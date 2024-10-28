package com.example.schedule.domain.db

import com.example.schedule.domain.model.StationCode
import kotlinx.coroutines.flow.Flow

interface StationCodeRepository {
    fun getStationCode(
        stationNameFrom: String,
        stationNameTo: String
    ): Flow<Pair<StationCode, StationCode>>
}
package com.example.schedule.domain.db

import com.example.schedule.domain.model.StationCode
import kotlinx.coroutines.flow.Flow

interface StationCodeRepository {
    fun getStationCode(stationName: String): Flow<StationCode>
}
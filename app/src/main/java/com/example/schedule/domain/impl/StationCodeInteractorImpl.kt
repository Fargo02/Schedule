package com.example.schedule.domain.impl

import com.example.schedule.domain.db.StationCodeInteractor
import com.example.schedule.domain.db.StationCodeRepository
import com.example.schedule.domain.model.StationCode
import kotlinx.coroutines.flow.Flow

class StationCodeInteractorImpl(
    private val repository: StationCodeRepository
): StationCodeInteractor {

    override suspend fun getStationCode(
        stationNameFrom: String,
        stationNameTo: String
    ): Flow<Pair<StationCode, StationCode>> {
        return repository.getStationCode(stationNameFrom, stationNameTo)
    }
}
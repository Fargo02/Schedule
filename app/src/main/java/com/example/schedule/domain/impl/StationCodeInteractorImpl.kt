package com.example.schedule.domain.impl

import com.example.schedule.domain.db.StationCodeInteractor
import com.example.schedule.domain.db.StationCodeRepository
import com.example.schedule.domain.model.StationCode
import kotlinx.coroutines.flow.Flow

class StationCodeInteractorImpl(
    private val stationCodeRepository: StationCodeRepository
): StationCodeInteractor {
    override fun getStationCode(stationName: String): Flow<StationCode> {
        return stationCodeRepository.getStationCode(stationName)
    }
}
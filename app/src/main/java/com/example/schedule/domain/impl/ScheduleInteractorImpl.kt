package com.example.schedule.domain.impl

import com.example.schedule.domain.api.ScheduleInteractor
import com.example.schedule.domain.api.ScheduleRepository
import com.example.schedule.domain.model.StationCode
import com.example.schedule.domain.model.StationInfo
import com.example.schedule.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScheduleInteractorImpl(
    private val repository: ScheduleRepository
): ScheduleInteractor {
    override fun getScheduleByStationCodeAndDate(
        fromCode: String,
        toCode: String,
        date: String,
        transportTypes: String,
    ): Flow<Pair<List<StationInfo>?, String?>> {
        return repository.getScheduleByStationCodeAndDate(
            fromCode = fromCode,
            toCode = toCode,
            date = date,
            transportTypes = transportTypes
        ).map { result ->
            when (result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }
                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }

    override suspend fun getAllStations() {
        repository.getAllStations()
    }

}
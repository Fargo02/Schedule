package com.example.schedule.domain.impl

import com.example.schedule.domain.api.ScheduleBetweenInteractor
import com.example.schedule.domain.api.ScheduleBetweenRepository
import com.example.schedule.domain.model.StationInfo
import com.example.schedule.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScheduleBetweenInteractorImpl(
    private val repository: ScheduleBetweenRepository
): ScheduleBetweenInteractor {
    override fun getScheduleByStationCodeAndDate(
        fromCode: String,
        toCode: String,
        date: String
    ): Flow<Pair<List<StationInfo>?, String?>> {
        return repository.getScheduleByStationCodeAndDate(fromCode = fromCode,
            toCode = toCode, date = date).map { result ->
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

}
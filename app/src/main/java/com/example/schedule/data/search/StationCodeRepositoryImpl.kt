package com.example.schedule.data.search

import com.example.schedule.domain.db.StationCodeRepository
import com.example.schedule.domain.model.StationCode
import com.practicum.mymovies.data.converters.StationDbConvertor
import com.practicum.mymovies.data.db.AppDatabase
import com.practicum.mymovies.data.db.entity.ScheduleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StationCodeRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: StationDbConvertor,
):StationCodeRepository {
    override fun getStationCode(stationName: String): Flow<List<StationCode>> = flow {
        val stations = appDatabase.scheduleDao().getScheduleByName(stationName)
        emit(convertFromScheduleEntity(stations))
    }
    private fun convertFromScheduleEntity(stations: List<ScheduleEntity>): List<StationCode> {
        return stations.map { station -> movieDbConvertor.map(station) }
    }
}
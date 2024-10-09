package com.example.schedule.data.search

import com.example.schedule.domain.db.StationCodeRepository
import com.example.schedule.domain.model.StationCode
import com.example.schedule.data.converters.StationDbConvertor
import com.example.schedule.data.db.AppDatabase
import com.practicum.mymovies.data.db.entity.ScheduleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StationCodeRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: StationDbConvertor,
):StationCodeRepository {
    override fun getStationCode(stationNameFrom: String, stationNameTo: String): Flow<Pair<StationCode, StationCode>> = flow {
        val from = appDatabase.scheduleDao().getScheduleByName(stationNameFrom)
        val to = appDatabase.scheduleDao().getScheduleByName(stationNameTo)
        emit(Pair(convertFromScheduleEntity(from), convertFromScheduleEntity(to)))
    }
    private fun convertFromScheduleEntity(station: ScheduleEntity): StationCode {
        return movieDbConvertor.map(station)
    }
}
package com.example.schedule.data.search

import com.example.schedule.data.db.entity.AllScheduleRequest
import com.example.schedule.data.db.entity.AllScheduleResponse
import com.example.schedule.data.dto.ScheduleRequest
import com.example.schedule.data.dto.ScheduleResponse
import com.example.schedule.domain.api.ScheduleRepository
import com.example.schedule.domain.model.StationCode
import com.example.schedule.domain.model.StationInfo
import com.example.schedule.utils.Resource
import com.practicum.mymovies.data.converters.StationDbConvertor
import com.example.schedule.data.db.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Locale

class ScheduleRepositoryImpl(
    private val networkClient: NetworkClient,
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: StationDbConvertor,
):ScheduleRepository {

    private val timeFormat by lazy { SimpleDateFormat("mm:ss", Locale.getDefault()) }

    override fun getScheduleByStationCodeAndDate(
        fromCode: String,
        toCode: String,
        date: String,
        transportTypes: String
        )
    : Flow<Resource<List<StationInfo>>> = flow {
        val response = networkClient.doRequest(ScheduleRequest(
            fromCode = fromCode,
            toCode = toCode,
            date = date,
            transportTypes = transportTypes
        ))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                with(response as ScheduleResponse) {
                    val data = segments.map {
                        StationInfo(
                            transportType = it.thread.transport_type,
                            shortTitle = it.thread.short_title,
                            companyName = it.thread.carrier.title,
                            vehicle = it.thread.vehicle,
                            departure = it.departure.drop(11).dropLast(9),
                            duration = if (it.duration > 60) timeFormat.format(it.duration.toLong()) else "00:01",
                            titleFrom = it.from.title,
                            titleTo = it.to.title,
                            arrival = it.arrival.drop(11).dropLast(9)
                        )
                    }
                    emit(Resource.Success(data))
                }
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }

    override fun getAllStations(): Flow<Resource<List<StationCode>>> = flow {
        val response = networkClient.doRequest(AllScheduleRequest())
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                with(response as AllScheduleResponse) {
                    val data = countries.flatMap { country ->
                        country.regions.flatMap { region ->
                            region.settlements.flatMap { selltent ->
                                selltent.stations.map { station ->
                                    StationCode(
                                        esrCode = station.codes.esr_code,
                                        yandexCode = station.codes.yandex_code,
                                        direction = station.direction,
                                        stationType = station.station_type,
                                        title = station.title,
                                        transportType = station.transport_type,
                                    )
                                }
                            }
                        }
                    }
                    saveStation(data)
                    emit(Resource.Success(data))
                }
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
    private suspend fun saveStation(movies: List<StationCode>) {
        val scheduleEntities = movies.map { movie -> movieDbConvertor.map(movie) }
        appDatabase.scheduleDao().insertSchedules(scheduleEntities)
    }
}
package com.example.schedule.data.search

import com.example.schedule.data.dto.ScheduleBetweenRequest
import com.example.schedule.data.dto.ScheduleBetweenResponse
import com.example.schedule.domain.api.ScheduleBetweenRepository
import com.example.schedule.domain.model.Station
import com.example.schedule.domain.model.StationAndDate
import com.example.schedule.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ScheduleBetweenRepositoryImpl(
    private val networkClient: NetworkClient
):ScheduleBetweenRepository {

    override fun getScheduleByStationCodeAndDate(
        fromCode: String,
        toCode: String,
        date: String,
        )
    : Flow<Resource<List<StationAndDate>>> = flow {
        val response = networkClient.doRequest(ScheduleBetweenRequest(
            fromCode = fromCode,
            toCode = toCode,
            date = date))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                with(response as ScheduleBetweenResponse) {
                    val data = segments.map {
                        StationAndDate(
                            arrival = it.arrival,
                            arrival_platform = it.arrival_platform,
                            arrival_terminal = it.arrival_terminal,
                            departure = it.departure,
                            departure_platform = it.departure_platform,
                            departure_terminal = it.departure_terminal,
                            duration = it.duration,
                            from = Station(
                                code = it.from.code,
                                popular_title = it.from.popular_title,
                                short_title = it.from.short_title,
                                station_type = it.from.station_type,
                                station_type_name = it.from.station_type_name,
                                title = it.from.title,
                                transport_type = it.from.transport_type,
                                type = it.from.type
                            ),
                            to = Station(
                                code = it.to.code,
                                popular_title = it.to.popular_title,
                                short_title = it.to.short_title,
                                station_type = it.to.station_type,
                                station_type_name = it.to.station_type_name,
                                title = it.to.title,
                                transport_type = it.to.transport_type,
                                type = it.to.type
                            ),
                            start_date = it.start_date,
                            stops = it.stops,
                            date = search.date
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
}
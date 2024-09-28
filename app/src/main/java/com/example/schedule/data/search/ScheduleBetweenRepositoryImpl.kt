package com.example.schedule.data.search

import com.example.schedule.data.dto.ScheduleBetweenRequest
import com.example.schedule.data.dto.ScheduleBetweenResponse
import com.example.schedule.domain.api.ScheduleBetweenRepository
import com.example.schedule.domain.model.StationInfo
import com.example.schedule.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class ScheduleBetweenRepositoryImpl(
    private val networkClient: NetworkClient
):ScheduleBetweenRepository {

    private val timeFormat by lazy { SimpleDateFormat("mm:ss", Locale.getDefault()) }
    private val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx")
    private val timeFormatter = DateTimeFormatter.ofPattern("mm:ss")

    override fun getScheduleByStationCodeAndDate(
        fromCode: String,
        toCode: String,
        date: String,
        )
    : Flow<Resource<List<StationInfo>>> = flow {
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
}
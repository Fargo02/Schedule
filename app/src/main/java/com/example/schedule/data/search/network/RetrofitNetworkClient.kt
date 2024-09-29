package com.example.schedule.data.search.network

import com.example.schedule.data.db.entity.AllScheduleRequest
import com.example.schedule.data.dto.Response
import com.example.schedule.data.dto.ScheduleRequest
import com.example.schedule.data.search.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitNetworkClient(
    private val scheduleApi: ScheduleApi,
): NetworkClient {

    override suspend fun doRequest(dto: Any): Response {
        return when (dto) {
            is ScheduleRequest -> {
                return withContext(Dispatchers.IO) {
                    try {
                        val response = scheduleApi.getScheduleByStationCodeAndDate(
                            fromCode = dto.fromCode,
                            toCode = dto.toCode,
                            date = dto.date,
                            transportTypes = dto.transportTypes
                            )
                        response.apply { resultCode = 200 }
                    } catch (e: Throwable) {
                        Response().apply { resultCode = 500 }
                    }
                }
            }
            is AllScheduleRequest -> {
                return withContext(Dispatchers.IO) {
                    try {
                        val response = scheduleApi.getAllStations()
                        response.apply { resultCode = 200 }
                    } catch (e: Throwable) {
                        Response().apply { resultCode = 500 }
                    }
                }
            }
            else -> {
                Response().apply { resultCode = 400 }
            }
        }
    }
}
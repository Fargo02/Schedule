package com.example.schedule.data.search.network

import com.example.schedule.data.dto.Response
import com.example.schedule.data.dto.ScheduleBetweenRequest
import com.example.schedule.data.search.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitNetworkClient(
    private val scheduleApi: ScheduleApi,
): NetworkClient {

    override suspend fun doRequest(dto: Any): Response {
        return when (dto) {
            is ScheduleBetweenRequest -> {
                return withContext(Dispatchers.IO) {
                    try {
                        val response = scheduleApi.getScheduleByStationCodeAndDate(
                            fromCode = dto.fromCode,
                            toCode = dto.toCode,
                            date = dto.date
                            )
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
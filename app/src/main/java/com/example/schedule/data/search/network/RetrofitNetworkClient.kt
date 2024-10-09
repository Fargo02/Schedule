package com.example.schedule.data.search.network

import android.util.Log
import com.example.schedule.data.db.entity.AllScheduleRequest
import com.example.schedule.data.dto.Response
import com.example.schedule.data.dto.ScheduleRequest
import com.example.schedule.data.search.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitNetworkClient(
    private val scheduleApi: ScheduleApi,
): NetworkClient {

    override suspend fun doRequestSuspend(dto: Any): Response {
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
            else -> {
                Response().apply { resultCode = 400 }
            }
        }
    }
    override fun doRequest(dto: Any): Response {
        return when (dto) {
            is AllScheduleRequest -> {
                val response = scheduleApi.getAllStations().execute()
                val body = response.body()
                return if (body != null) {
                    Log.i("Request", "${response.code()}")
                    body.apply { resultCode = response.code() }
                } else {
                    Response().apply { resultCode = response.code() }
                }
            }
            else -> {
                Response().apply { resultCode = 400 }
            }
        }
    }
}
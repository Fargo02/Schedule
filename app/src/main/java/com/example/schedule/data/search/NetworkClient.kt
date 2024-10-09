package com.example.schedule.data.search

import com.example.schedule.data.dto.Response

interface NetworkClient {
    suspend fun doRequestSuspend(dto: Any): Response

    fun doRequest(dto: Any): Response
}
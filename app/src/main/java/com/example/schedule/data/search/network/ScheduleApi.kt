package com.example.schedule.data.search.network

import com.example.schedule.data.dto.ScheduleBetweenResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleApi {

    @GET("./search/?transport_types=suburban")
    suspend fun getScheduleByStationCodeAndDate(
        @Query("apikey") apikey: String = "2c748246-2b5d-45f6-8881-d72f287f28f1",
        @Query("from") fromCode: String,
        @Query("to") toCode: String,
        @Query("date") date: String,
    ): ScheduleBetweenResponse

}
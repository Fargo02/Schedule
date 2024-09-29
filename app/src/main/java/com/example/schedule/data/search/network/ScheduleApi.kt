package com.example.schedule.data.search.network

import com.example.schedule.data.db.entity.AllScheduleResponse
import com.example.schedule.data.dto.ScheduleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleApi {

    @GET("./search/?")
    suspend fun getScheduleByStationCodeAndDate(
        @Query("apikey") apikey: String = "2c748246-2b5d-45f6-8881-d72f287f28f1",
        @Query("from") fromCode: String,
        @Query("to") toCode: String,
        @Query("date") date: String,
        @Query("transport_types") transportTypes: String,
    ): ScheduleResponse

    @GET("./stations_list/?apikey=2c748246-2b5d-45f6-8881-d72f287f28f1&lang=ru_RU&format=json")
    suspend fun getAllStations(): AllScheduleResponse

}
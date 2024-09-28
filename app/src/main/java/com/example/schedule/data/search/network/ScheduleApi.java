package com.example.schedule.data.search.network;

import com.example.schedule.data.dto.ScheduleBetweenResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ScheduleApi {
    @GET("./search/?transport_types=suburban")
    Call<ScheduleBetweenResponse> getScheduleByStationCodeAndDate(
            @Query("apikey") String apikey,
            @Query("from") String fromCode,
            @Query("to") String toCode,
            @Query("date") String date,
            @Query("transport_types") String transportTypes,
            @Query("lang") String lang
    );
}

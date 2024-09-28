package com.example.schedule.data.search.network;

import android.util.Log;

import com.example.schedule.data.dto.Response;
import com.example.schedule.data.dto.ScheduleBetweenRequest;
import com.example.schedule.data.dto.ScheduleBetweenResponse;
import com.example.schedule.data.search.NetworkClient;

import java.io.IOException;

import retrofit2.Call;

public class RetrofitNetworkClient implements NetworkClient {

    private final ScheduleApi service;

    public RetrofitNetworkClient(ScheduleApi service) {
        this.service = service;
    }

    @Override
    public <T> Response doRequest(T dto) {
        if (dto instanceof ScheduleBetweenRequest) {
            ScheduleBetweenRequest categoryRequest = (ScheduleBetweenRequest) dto;
            Call<ScheduleBetweenResponse> call = service.getScheduleByStationCodeAndDate(
                    "",
                    categoryRequest.fromCode,
                    categoryRequest.toCode,
                    categoryRequest.date,
                    categoryRequest.transportTypes,
                    ""
                    );
            try {
                Response<ScheduleBetweenResponse> response = call.execute();
                ScheduleBetweenResponse body = response.body();
                if (body != null) {
                    Log.i("Request", "${response.code()}");
                    body.setResultCode(response.code());
                    return (R) body;
                } else {
                    CategoryResponse emptyResponse = new CategoryResponse();
                    emptyResponse.setResultCode(response.code());
                    return (R) emptyResponse;
                }
            } catch (IOException e) {
                Log.e("NetworkError", "Network error: " + e.getMessage());
                return null;
            }
        } else {
            CategoryResponse errorResponse = new CategoryResponse();
            errorResponse.setResultCode(400);
            return (R) errorResponse;
        }
    }
}

package com.example.schedule.data.search;

import com.example.schedule.data.dto.Response;

public interface NetworkClient {
    <T> Response doRequest(T dto);
}

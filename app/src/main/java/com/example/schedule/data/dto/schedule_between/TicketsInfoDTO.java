package com.example.schedule.data.dto.schedule_between;

import java.util.ArrayList;

import lombok.Data;

@Data
public class TicketsInfoDTO {
    public boolean et_marker;
    public ArrayList<PlaceDTO> places;
}

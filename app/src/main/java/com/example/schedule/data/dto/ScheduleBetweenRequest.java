package com.example.schedule.data.dto;

import lombok.Data;

@Data
public class ScheduleBetweenRequest {
    public String fromCode;
    public String toCode;
    public String date;
    public String transportTypes;
}

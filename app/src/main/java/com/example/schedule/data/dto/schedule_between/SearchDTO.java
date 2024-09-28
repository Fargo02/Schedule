package com.example.schedule.data.dto.schedule_between;

import lombok.Data;

@Data
public class SearchDTO {
    public FromDTO from;
    public ToDTO myto;
    public String date;
}

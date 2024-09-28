package com.example.schedule.data.dto.schedule_between;

import lombok.Data;

@Data
public class Pagination{
    public int total;
    public int limit;
    public int offset;
}

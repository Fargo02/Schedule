package com.example.schedule.data.dto;

import com.example.schedule.data.dto.schedule_between.Pagination;
import com.example.schedule.data.dto.schedule_between.SearchDTO;
import com.example.schedule.data.dto.schedule_between.SegmentDTO;

import java.util.List;

import lombok.Data;

@Data
public class ScheduleBetweenResponse extends Response {
    public SearchDTO search;
    public List<SegmentDTO> segments;
    public Pagination pagination;
}

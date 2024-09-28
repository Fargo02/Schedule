package com.example.schedule.data.dto.schedule_between;

import lombok.Data;

@Data
public class SegmentDTO {
    public ThreadDTO thread;
    public String stops;
    public FromDTO from;
    public ToDTO myto;
    public String departure_platform;
    public String arrival_platform;
    public String departure_terminal;
    public String arrival_terminal;
    public int duration;
    public boolean has_transfers;
    public TicketsInfoDTO tickets_info;
    public String departure;
    public String arrival;
    public String start_date;
}

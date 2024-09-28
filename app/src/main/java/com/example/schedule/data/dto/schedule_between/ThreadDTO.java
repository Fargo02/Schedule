package com.example.schedule.data.dto.schedule_between;

import lombok.Data;

@Data
public class ThreadDTO {
    public String number;
    public String title;
    public String short_title;
    public String  express_type;
    public String transport_type;
    public CarrierDTO carrier;
    public String uid;
    public String vehicle;
    public TransportSubtypeDTO transport_subtype;
    public String thread_method_link;
}
